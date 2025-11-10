package com.school.schoolSystem.model;

import java.time.LocalDate;

public class Enrollment {

    private int studentId;
    private int courseId;
    private LocalDate regDate;

    public Enrollment(int studentId, int courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.regDate = LocalDate.now();
    }

    public int getStudentId() {
        return studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "studentId=" + studentId +
                ", courseId=" + courseId +
                ", regDate=" + regDate +
                '}';
    }
}
