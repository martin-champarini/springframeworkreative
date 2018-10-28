package com.example.springframework.reactive.sfreactive;

import com.example.springframework.reactive.sfreactive.model.Person;
import com.example.springframework.reactive.sfreactive.model.PersonCommand;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.*;

@Slf4j
public class SfReactiveTest {
    Person michael = new Person("Michael","Watzon");
    Person fiona = new Person("Fiona","Porter");
    Person petr = new Person("Petr", "Cech");
    
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void monoTest() {
        Mono<Person> personMono = Mono.just(michael);

        Person person = personMono.block();

        log.info(person.sayMyName());
    }


    @Test
    public void monoTransform() throws Exception {
        //create new person mono
        Mono<Person> personMono = Mono.just(fiona);

        PersonCommand command = personMono
                .map(person -> { //type transformation
                    return new PersonCommand(person);
                }).block();

        log.info(command.sayMyName());
    }

    @Test(expected = NullPointerException.class)
    public void monoFilter() throws Exception {
        Mono<Person> personMono = Mono.just(petr);

        //filter example
        Person samAxe = personMono
                .filter(person -> person.getFirstName().equalsIgnoreCase("foo"))
                .block();


        log.info(samAxe.sayMyName()); //throws NPE
    }

    @Test
    public void fluxTest() throws Exception {

        Flux<Person> people = Flux.just(michael, fiona, petr);

        people.subscribe(person -> log.info(person.sayMyName()));

    }

    @Test
    public void fluxTestFilter() throws Exception {

        Flux<Person> people = Flux.just(michael, fiona, petr);

        people.filter(person -> person.getFirstName().equals(fiona.getFirstName()))
                .subscribe(person -> log.info(person.sayMyName()));

    }

    @Test
    public void fluxTestDelayNoOutput() throws Exception {

        Flux<Person> people = Flux.just(michael, fiona, petr);

        people.delayElements(Duration.ofSeconds(1))
                .subscribe(person -> log.info(person.sayMyName()));

    }

    @Test
    public void fluxTestDelay() throws Exception {

        CountDownLatch countDownLatch = new CountDownLatch(1);

        Flux<Person> people = Flux.just(michael, fiona, petr);

        people.delayElements(Duration.ofSeconds(1))
                .doOnComplete(countDownLatch::countDown)
                .subscribe(person -> log.info(person.sayMyName()));

        countDownLatch.await();

    }

    @Test
    public void fluxTestFilterDelay() throws Exception {

        CountDownLatch countDownLatch = new CountDownLatch(1);

        Flux<Person> people = Flux.just(michael, fiona, petr);

        people.delayElements(Duration.ofSeconds(1))
                .filter(person -> person.getFirstName().contains("i"))
                .doOnComplete(countDownLatch::countDown)
                .subscribe(person -> log.info(person.sayMyName()));

        countDownLatch.await();
    }
}