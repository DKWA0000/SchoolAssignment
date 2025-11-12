package com.school.schoolSystem.dto;

import com.school.schoolSystem.model.Student;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class CourseResponseDTO {

    private int id;
    private String title;
    private String teacher;
    private int maxStudents;
    private boolean active;

    public CourseResponseDTO(int id, String title, String teacher, int maxStudents,  boolean active) {
        this.id = id;
        this.title = title;
        this.teacher = teacher;
        this.maxStudents = maxStudents;
        this.active = active;
    }
}
