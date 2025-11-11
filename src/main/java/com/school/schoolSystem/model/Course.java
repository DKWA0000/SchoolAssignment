package com.school.schoolSystem.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100)
    private String title;

    @Column(length = 100)
    private String teacher;

    @Column(length = 100, name = "max_students")
    private int maxStudents;

    @Column(name = "active", columnDefinition="BOOLEAN DEFAULT true")
    private boolean active;

    public Course(int id, String title, String teacher, int maxStudents, boolean active) {
        this.id = id;
        this.title = title;
        this.teacher = teacher;
        this.maxStudents = maxStudents;
        this.active = active;
    }

    public Course(String title, String teacher, int maxStudents, boolean active) {
        this.title = title;
        this.teacher = teacher;
        this.maxStudents = maxStudents;
        this.active = active;
    }

    public Course() {

    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", teacher='" + teacher + '\'' +
                ", maxStudents=" + maxStudents +

                ", active=" + active +
                '}';
    }
}
