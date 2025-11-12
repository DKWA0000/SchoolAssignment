package com.school.schoolSystem.dto;

public class StudentRequestDTO {
    private int id;
    private String name;
    private int age;
    private String email;


     public StudentRequestDTO(int id, String name, int age, String email){
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

}
