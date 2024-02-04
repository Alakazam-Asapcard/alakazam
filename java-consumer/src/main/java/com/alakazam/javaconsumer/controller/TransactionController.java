package com.alakazam.javaconsumer.controller;

import com.alakazam.javaconsumer.dto.PayloadDto;
import com.alakazam.javaconsumer.model.Installment;
import com.alakazam.javaconsumer.model.Person;
import com.alakazam.javaconsumer.model.Transaction;
import com.alakazam.javaconsumer.repository.InstallmentRepository;
import com.alakazam.javaconsumer.repository.PersonRepository;
import com.alakazam.javaconsumer.repository.TransactionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

public class TransactionController {

	@Autowired
	public PersonRepository personRepository;

	@Autowired
	public TransactionRepository transactionRepository;

	@Autowired
	public InstallmentRepository installmentRepository;

	@RabbitListener(queues = "#{autoDeleteQueue.name}")
	public void receive(String payload) throws JsonProcessingException {

		//System.out.println("Vim pra ca!");

		ObjectMapper objectMapper = new ObjectMapper();
		PayloadDto dto = objectMapper.readValue(payload, PayloadDto.class);

		StopWatch watch = new StopWatch();
		watch.start();

		System.out.println("Iniciei as parada");

		personRepository.save(new Person(dto.getPerson_id(), dto.getName(),dto.getAge()));
		Optional<Person> person = personRepository.findById(dto.getPerson_id());

		System.out.println("Salvei person");

        person.ifPresent(value -> transactionRepository.save(new Transaction(dto.getTransaction_id(), value, dto.getTransaction_date(), dto.getAmount())));
		Optional<Transaction> transaction = transactionRepository.findById(dto.getTransaction_id());

		transaction.ifPresent(value -> installmentRepository.save(new Installment(value, dto.getInstallment_number(), dto.getAmount().divide(new BigDecimal(dto.getInstallment_number())))));

		System.out.println(" [x] Saved '" + payload + "' to database");
		watch.stop();
		System.out.println(" [x] Done in " + watch.getTotalTimeSeconds() + "s");
	}

}
