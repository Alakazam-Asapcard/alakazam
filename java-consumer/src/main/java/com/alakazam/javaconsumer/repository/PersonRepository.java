package com.alakazam.javaconsumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alakazam.javaconsumer.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, String>{

}
