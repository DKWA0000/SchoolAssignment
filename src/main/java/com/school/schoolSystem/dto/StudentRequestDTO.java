package com.school.schoolSystem.dto;

import lombok.Getter;

@Getter
public class StudentRequestDTO {
    private String name;
    private int age;
    private String email;


     public StudentRequestDTO(String name, int age, String email){
        this.name = name;
        this.age = age;
        this.email = email;
    }

}
