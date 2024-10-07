package com.week3.assignment1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data  // Generates getters, setters, toString, equals, and hashCode
@NoArgsConstructor  // Generates default constructor
@AllArgsConstructor  // Generates constructor with all fields
public class Patient {
    private Long id;
    private String name;
    private int age;
    private String gender;
    private String email;
    private String city;
    private LocalDate dateOfBirth;
}
