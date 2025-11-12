package com.school.schoolSystem.dto;

import java.util.List;

public class EnrolledStudentsDTO {

    private String course_title;
    private List<StudentEnrollDTO> students;

    public EnrolledStudentsDTO(String course_title, List<StudentEnrollDTO> students) {
        this.course_title = course_title;
        this.students = students;
    }

    public String getCourse_title() {
        return course_title;
    }

    public List<StudentEnrollDTO> getStudents() {
        return students;
    }

    @Override
    public String toString() {
        return "EnrolledStudentsDTO{" +
                "course_title='" + course_title + '\'' +
                ", students=" + students +
                '}';
    }
}
