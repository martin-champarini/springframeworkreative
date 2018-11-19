package com.example.springframework.reactive.sfreactive.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonCommand {
    private String firstName;
    private String lastName;

    public PersonCommand(Person person) {
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
    }
    public String sayMyName() {
        return "My Name is : "+ firstName + " " + lastName + ".";
    }
}
