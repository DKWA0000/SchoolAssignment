package com.school.schoolSystem.model;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Entity
@Table(name = "enrollments")
public class Enrollment {

    @Id
    @Column(name = "enrollment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int enrollemtId;


    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student studentId;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course courseId;

    @Column(name = "active", columnDefinition="BOOLEAN DEFAULT true")
    private boolean active;

    @Column(name = "reg_date")
    private LocalDate regDate;

    @Column(name = "course_grade")
    private int grade;

    public Enrollment(Student studentId, Course courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.active = true;
        this.regDate = LocalDate.now();
        this.grade = -1;
    }

    public Enrollment(int enrollemtId,Student studentId, Course courseId, int grade, boolean active) {
        this.enrollemtId = enrollemtId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.active = active;
        this.regDate = LocalDate.now();
        this.grade = grade;
    }

    public Enrollment() {

    }

    public int getEnrollemtId(){
        return enrollemtId;
    }

    public Student getStudentId() {
        return studentId;
    }

    public Course getCourseId() {
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

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "enrollemtId=" + enrollemtId +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                ", active=" + active +
                ", regDate=" + regDate +
                ", grade=" + grade +
                '}';
    }
}
