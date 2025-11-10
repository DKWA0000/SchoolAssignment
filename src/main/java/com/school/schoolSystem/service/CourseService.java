package com.school.schoolSystem.service;

import com.school.schoolSystem.repository.CourseRepository;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    private CourseRepository repo;

    public CourseService(CourseRepository repo) {
        this.repo = repo;
    }
}
