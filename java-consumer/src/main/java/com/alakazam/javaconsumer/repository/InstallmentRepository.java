package com.alakazam.javaconsumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alakazam.javaconsumer.model.Installment;

public interface InstallmentRepository extends JpaRepository<Installment, Long> {

}
