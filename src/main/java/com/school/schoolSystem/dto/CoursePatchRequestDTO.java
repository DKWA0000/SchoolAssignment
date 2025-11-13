package com.school.schoolSystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CoursePatchRequestDTO {

    private String title;
    private String teacher;
    @Pattern(regexp = "\\d+", message = "maxStudents must be numeric")
    private String maxStudents;

    public CoursePatchRequestDTO(String title, String teacher, String maxStudents) {
        this.title = title;
        this.teacher = teacher;
        this.maxStudents = maxStudents;
    }
}
