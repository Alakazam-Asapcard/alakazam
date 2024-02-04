package com.alakazam.javaconsumer.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_transactions")
public class Transaction {

	@Id
	private String id;

	@NotBlank
	@Column(name = "person_id")
	@ManyToOne
	@JsonIgnoreProperties("transactions")
	private Person personId;

	@NotNull
	@Column(name = "transaction_date")
	private LocalDateTime transactionDate;

	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private BigDecimal amount;

	/*@ManyToOne
	@JsonIgnoreProperties("transactions")
	private Person person;*/

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "transaction", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("transaction_id")
	private List<Installment> installments;
	
	public Transaction(String id, Person personId, LocalDateTime transactionDate, BigDecimal amount) {
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

	public Person getPersonId() {
		return personId;
	}

	public void setPersonId(Person personId) {
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
