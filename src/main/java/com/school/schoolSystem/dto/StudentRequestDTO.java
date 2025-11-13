package com.school.schoolSystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import org.springframework.format.annotation.NumberFormat;

@Getter
public class StudentRequestDTO {

    @NotBlank(message = "Name is required and cannot be blank")
    private String name;

    @NotNull(message = "Age is required and cannot be null")
    @Positive(message = "Age must be a positive number")
    private Integer age;

    @NotBlank(message = "Email is required and cannot be blank")
    @Email(message = "Email must be valid")
    private String email;


     public StudentRequestDTO(String name, int age, String email){
        this.name = name;
        this.age = age;
        this.email = email;
    }

}
