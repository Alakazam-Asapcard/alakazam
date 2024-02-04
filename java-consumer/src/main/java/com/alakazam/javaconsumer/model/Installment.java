package com.alakazam.javaconsumer.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_installments")
public class Installment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@JoinColumn(name = "transaction_id")
	@ManyToOne
	@JsonIgnoreProperties("installments")
	private Transaction transactionId;

	@NotNull
	@Column(name = "installment_number")
	private int installmentNumber;

	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private BigDecimal value;
	
	public Installment(@NotBlank Transaction transactionId, @NotNull int installmentNumber, @NotNull BigDecimal value) {
		this.transactionId = transactionId;
		this.installmentNumber = installmentNumber;
		this.value = value;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Transaction getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Transaction transactionId) {
		this.transactionId = transactionId;
	}

	public int getInstallmentNumber() {
		return installmentNumber;
	}

	public void setInstallmentNumber(int installmentNumber) {
		this.installmentNumber = installmentNumber;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

}
