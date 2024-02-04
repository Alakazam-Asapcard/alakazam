package com.alakazam.javaconsumer.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_transactions")
public class Transaction {
    
    @Id
    private String id;

    @NotNull
    @Column(name = "person_id")
    private String personId;

    @NotNull
    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal amount;

    public Transaction(String id, String personId, LocalDateTime transactionDate, BigDecimal amount) {
        this.id = id;
        this.personId = personId;
        this.transactionDate = transactionDate;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
