package com.school.schoolSystem.dto;

public class EnrollDTO {

    private int studentId, courseId;

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
