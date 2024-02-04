package com.alakazam.javaconsumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alakazam.javaconsumer.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {

}
