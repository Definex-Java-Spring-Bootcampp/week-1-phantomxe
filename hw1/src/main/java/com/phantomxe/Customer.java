package com.phantomxe;

import java.time.LocalDate;
import java.time.Period;

public class Customer {
    private String name;
    private String email;
    private LocalDate dateOfBirth;

    public Customer(String name, String email, LocalDate dateOfBirth) {
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() { 
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return "Customer [name=" + name + ", email=" + email + ", dateOfBirth=" + dateOfBirth + "]";
    }
}
