package com.school.schoolSystem.dto;

import jakarta.validation.constraints.PositiveOrZero;

public class EnrollDTO {

    @PositiveOrZero
    private int studentId;
    @PositiveOrZero
    private int courseId;

    public EnrollDTO(int studentId, int courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    @Override
    public String toString() {
        return "EnrollDTO{" +
                "studentId=" + studentId +
                ", courseId=" + courseId +
                '}';
    }
}
