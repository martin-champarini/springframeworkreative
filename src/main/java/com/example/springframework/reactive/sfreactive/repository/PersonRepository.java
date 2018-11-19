package com.example.springframework.reactive.sfreactive.repository;

import com.example.springframework.reactive.sfreactive.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
}
