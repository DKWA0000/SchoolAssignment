package com.school.schoolSystem.repository;

import com.school.schoolSystem.dto.EnrollDTO;
import com.school.schoolSystem.model.Enrollment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EnrollmentRepository {


    private final List<Enrollment> enrollments;
    private int currentId;

    public EnrollmentRepository()
    {
        this.enrollments = new ArrayList<>();
        this.currentId = 1;
    }

    public boolean checkIfStudentRegistered(EnrollDTO dto){
        return enrollments.stream()
                .map(enrollment ->
                        (enrollment.getStudentId() == dto.getStudentId()) &&
                        (enrollment.getCourseId() == dto.getCourseId())).findFirst().get();
    }

    public void addEnrollment(EnrollDTO dto){
        this.enrollments.add(new Enrollment(currentId++, dto.getStudentId(), dto.getCourseId()));
    }

    public void makeEnrollmentInactive(EnrollDTO dto){
        this.enrollments.stream().filter(enrollment ->
                (enrollment.getStudentId() == dto.getStudentId()) &&
                (enrollment.getCourseId() == dto.getCourseId())).findFirst()
                .get().setActive(false);
    }

    public List<Integer> getEnrolledStudents(int courseId){
        return enrollments.stream().filter(
                enrollment -> (enrollment.getCourseId() == courseId) && (enrollment.isActive()))
                .map(enrollment -> enrollment.getStudentId()).toList();
    }
}
