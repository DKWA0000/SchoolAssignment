package com.school.schoolSystem.dto;


import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
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

    public String getMaxStudents() {
        return maxStudents;
    }

    public void setMaxStudents(String maxStudents) {
        this.maxStudents = maxStudents;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
