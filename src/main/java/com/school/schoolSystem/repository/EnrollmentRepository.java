package com.school.schoolSystem.repository;

import com.school.schoolSystem.model.Enrollment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EnrollmentRepository {

    private List<Enrollment> enrollments;

    public EnrollmentRepository(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }
}
