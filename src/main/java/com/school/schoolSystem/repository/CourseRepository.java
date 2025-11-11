package com.school.schoolSystem.repository;

import com.school.schoolSystem.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    Optional<Course> findByTitle(String title);
    List<Course> findAll();
    List<Course> findAllByTeacher(String teacher);
}
