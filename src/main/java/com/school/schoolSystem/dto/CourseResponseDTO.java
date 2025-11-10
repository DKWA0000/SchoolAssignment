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
    private List<Student> students;

    public CourseResponseDTO(int id, String title, String teacher, int maxStudents,  List<Student> students) {
        this.id = id;
        this.title = title;
        this.teacher = teacher;
        this.maxStudents = maxStudents;
        this.students = students;
    }
}
