package com.school.schoolSystem.service;

import com.school.schoolSystem.repository.StudentRepositoryOld;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private StudentRepositoryOld repo;

    public StudentService(StudentRepositoryOld repo) {
        this.repo = repo;
    }
}
