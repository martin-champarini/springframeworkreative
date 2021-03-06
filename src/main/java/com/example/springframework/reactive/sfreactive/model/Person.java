package com.example.springframework.reactive.sfreactive.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private Long id;
    private String firstName;
    private String lastName;

    public String sayMyName() {
        return "My Name is : "+ firstName + " " + lastName + ".";
    }
}
