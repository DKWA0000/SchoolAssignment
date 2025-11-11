package com.school.schoolSystem.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class Course {

    private int id;
    private String title;
    private String teacher;
    private int maxStudents;
    private List<Student> students;
    private boolean active;

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", teacher='" + teacher + '\'' +
                ", maxStudents=" + maxStudents +
                ", students=" + students +
                ", active=" + active +
                '}';
    }
}
