package com.example.springframework.reactive.sfreactive.repository.reactive;

import com.example.springframework.reactive.sfreactive.model.Person;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PersonReativeRepository extends ReactiveMongoRepository<Person, Long> {
}
