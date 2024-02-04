package com.alakazam.javaconsumer.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_transactions")
public class Transaction {

	@Id
	@NotBlank
	private String id;

	@ManyToOne
	@JsonIgnoreProperties("transactions")
	private Person person;

	@NotNull
	@Column(name = "transaction_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private LocalDateTime transaction_date;

	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private BigDecimal amount;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "transaction", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("transaction")
	private List<Installment> installments;
	
	public Transaction(String id, Person person, LocalDateTime transaction_date, BigDecimal amount) {
		this.id = id;
		this.person = person;
		this.transaction_date = transaction_date;
		this.amount = amount;
	}

	public Transaction() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public LocalDateTime getTransaction_date() {
		return transaction_date;
	}

	public void setTransaction_date(LocalDateTime transaction_date) {
		this.transaction_date = transaction_date;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}
