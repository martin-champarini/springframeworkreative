package com.example.springframework.reactive.sfreactive.bootstrap;

import com.example.springframework.reactive.sfreactive.model.Person;
import com.example.springframework.reactive.sfreactive.repository.PersonRepository;
import com.example.springframework.reactive.sfreactive.repository.reactive.PersonReativeRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Slf4j
public class BootstrapData implements ApplicationListener<ContextRefreshedEvent> {

    private PersonRepository personRepository;

    @Autowired
    private PersonReativeRepository personReativeRepository;


    public BootstrapData(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadPersons();

        log.info("Reactive test log = {}", personReativeRepository.count().block());
    }

    public void loadPersons() {
        Person ashleyCole = new Person(1L,"Ashley","Cole");
        Person joeSmith = new Person(1L,"Joe","Smith");
        personRepository.save(ashleyCole);
        personRepository.save(joeSmith);
    }
}
