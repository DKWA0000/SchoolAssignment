package com.school.schoolSystem.model;

import java.time.LocalDate;

public class Enrollment {

    private int enrollemtId;
    private int studentId;
    private int courseId;
    private boolean active;
    private LocalDate regDate;

    public Enrollment(int enrollmentId, int studentId, int courseId) {
        this.enrollemtId = enrollmentId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.active = true;
        this.regDate = LocalDate.now();
    }

    public int getEnrollemtId(){
        return enrollemtId;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "enrollemtId=" + enrollemtId +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                ", active=" + active +
                ", regDate=" + regDate +
                '}';
    }
}
