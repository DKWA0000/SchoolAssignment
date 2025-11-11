package com.school.schoolSystem.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class StudentResponseDTO {
    private int id;
    private String name;
    private int age;
    private String email;

    public StudentResponseDTO(int id, String name, int age, String email){
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

}
