package com.alakazam.javaconsumer.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {

}
