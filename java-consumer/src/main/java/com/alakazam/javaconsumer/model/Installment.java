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

	@ManyToOne
	@JsonIgnoreProperties("installments")
	private Transaction transaction;

	@NotNull
	@Column(name = "installment_number")
	private int installment_number;

	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private BigDecimal value;
	
	public Installment(@NotBlank Transaction transaction, @NotNull int installment_number, @NotNull BigDecimal value) {
		this.transaction = transaction;
		this.installment_number = installment_number;
		this.value = value;
	}

	public Installment() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public int getInstallment_number() {
		return installment_number;
	}

	public void setInstallment_number(int installment_number) {
		this.installment_number = installment_number;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

}
