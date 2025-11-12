package com.school.schoolSystem.dto;

import jakarta.validation.constraints.PositiveOrZero;
import org.hibernate.validator.constraints.Range;

public class EnrollGradeDTO {

    @PositiveOrZero
    private int studentId;
    @PositiveOrZero
    private int courseId;
    @Range(min = 0, max = 100)
    private int grade;

    public EnrollGradeDTO(int studentId, int courseId, int grade) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.grade = grade;
    }

    public int getCourseId() {
        return courseId;
    }

    public int getGrade() {
        return grade;
    }

    public int getStudentId() {
        return studentId;
    }

    @Override
    public String toString() {
        return "EnrollGradeDTO{" +
                "studentId=" + studentId +
                ", courseId=" + courseId +
                ", grade=" + grade +
                '}';
    }
}
