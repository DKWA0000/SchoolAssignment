package com.school.schoolSystem.service;

import com.school.schoolSystem.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentService {

    private EnrollmentRepository repo;

    public EnrollmentService(EnrollmentRepository repo) {
        this.repo = repo;
    }
}
