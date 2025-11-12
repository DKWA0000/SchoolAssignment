package com.school.schoolSystem.dto;

import java.time.LocalDate;

public class StudentEnrollDTO {

    private String name;
    private int age;
    private String email;
    private LocalDate date;
    private boolean active;

    public StudentEnrollDTO(String name, int age, String email, LocalDate date) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.date = date;
        this.active = true;
    }

    public boolean isActive() {
        return active;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "StudentResponseDTO{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", active=" + active +
                '}';
    }
}
