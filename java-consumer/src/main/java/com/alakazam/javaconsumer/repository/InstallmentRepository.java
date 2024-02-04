package com.alakazam.javaconsumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alakazam.javaconsumer.model.Installment;
Added import org.springframework.stereotype.Repository;

@Repository
public interface InstallmentRepository extends JpaRepository<Installment, Long> {

}
