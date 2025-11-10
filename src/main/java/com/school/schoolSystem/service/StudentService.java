package com.school.schoolSystem.service;

import com.school.schoolSystem.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }
}
