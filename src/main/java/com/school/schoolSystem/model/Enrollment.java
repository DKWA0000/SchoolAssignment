package com.school.schoolSystem.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "enrollments")
public class Enrollment {

    @Id
    @Column(name = "enrollment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int enrollemtId;



    private int studentId;
    private int courseId;

    @Column(name = "active", columnDefinition="BOOLEAN DEFAULT true")
    private boolean active;

    @Column(name = "reg_date")
    private LocalDate regDate;

    public Enrollment(int enrollmentId, int studentId, int courseId) {
        this.enrollemtId = enrollmentId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.active = true;
        this.regDate = LocalDate.now();
    }

    public Enrollment() {

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
