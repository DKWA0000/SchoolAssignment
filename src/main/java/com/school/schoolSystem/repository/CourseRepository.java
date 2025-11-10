package com.school.schoolSystem.repository;

import com.school.schoolSystem.model.Course;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseRepository {

    private List<Course> courses;

    public CourseRepository(List<Course> courses) {
        this.courses = courses;
    }

}
