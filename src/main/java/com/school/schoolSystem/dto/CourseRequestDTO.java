package com.school.schoolSystem.dto;


import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CourseRequestDTO {

    @NotEmpty(message = "Course title is mandatory")
    @NotBlank(message = "Course title is mandatory")
    private String title;

    @NotEmpty(message = "Course teacher is mandatory")
    @NotBlank(message = "Course teacher is mandatory")
    private String teacher;

    @NotEmpty(message = "Number of max students is mandatory")
    @Pattern(regexp = "\\d+", message = "maxStudents must be numeric")
    private String maxStudents;

    public CourseRequestDTO(String title, String teacher, String maxStudents) {
        this.title = title;
        this.teacher = teacher;
        this.maxStudents = maxStudents;
    }


}
