package com.alakazam.javaconsumer.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PayloadDto {

    private String transaction_id;

    private LocalDateTime transaction_date;

    private String person_id;

    private String name;

    private int age;

    private BigDecimal amount;

    private int installment_number;

    public PayloadDto(String transaction_id, LocalDateTime transaction_date, String person_id, String name, int age, BigDecimal amount, int installment_number) {
        this.transaction_id = transaction_id;
        this.transaction_date = transaction_date;
        this.person_id = person_id;
        this.name = name;
        this.age = age;
        this.amount = amount;
        this.installment_number = installment_number;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public LocalDateTime getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(LocalDateTime transaction_date) {
        this.transaction_date = transaction_date;
    }

    public String getPerson_id() {
        return person_id;
    }

    public void setPerson_id(String person_id) {
        this.person_id = person_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getInstallment_number() {
        return installment_number;
    }

    public void setInstallment_number(int installment_number) {
        this.installment_number = installment_number;
    }
}
