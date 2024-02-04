package com.alakazam.javaconsumer.controller;

import com.alakazam.javaconsumer.dto.PayloadDto;
import com.alakazam.javaconsumer.model.Installment;
import com.alakazam.javaconsumer.model.Person;
import com.alakazam.javaconsumer.model.Transaction;
import com.alakazam.javaconsumer.repository.InstallmentRepository;
import com.alakazam.javaconsumer.repository.PersonRepository;
import com.alakazam.javaconsumer.repository.TransactionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public class TransactionController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private InstallmentRepository installmentRepository;

    @RabbitListener(queues = "#{autoDeleteQueue.name}")
    public void receive(String payload) throws JsonProcessingException {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            PayloadDto dto = objectMapper.readValue(payload, PayloadDto.class);

            StopWatch watch = new StopWatch();
            watch.start();

            Person newPerson = new Person(dto.getPerson_id(), dto.getName(), dto.getAge());
            personRepository.saveAndFlush(newPerson);

            Transaction newTransaction = new Transaction(dto.getTransaction_id(), newPerson, dto.getTransaction_date(), dto.getAmount());
            transactionRepository.save(newTransaction);

            Installment newInstallment = new Installment(newTransaction, dto.getInstallment_number(), dto.getAmount().divide(new BigDecimal(dto.getInstallment_number()), 2, RoundingMode.CEILING));
            installmentRepository.save(newInstallment);

            System.out.println(" [x] Saved to database");
            watch.stop();
            System.out.println(" [x] Done in " + watch.getTotalTimeSeconds() + "s");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
