package com.school.schoolSystem.dto;

import java.time.LocalDate;

public class StudentEnrollDTO {

    private String name;
    private int age;
    private String email;
    private LocalDate date;
    private boolean active;
    private int grade;

    public StudentEnrollDTO(String name, int age, String email, LocalDate date, boolean active, int grade) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.date = date;
        this.active = active;
        this.grade = grade;
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

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "StudentEnrollDTO{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", date=" + date +
                ", active=" + active +
                ", grade=" + grade +
                '}';
    }
}
