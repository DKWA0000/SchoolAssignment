package com.school.schoolSystem.repository;

import com.school.schoolSystem.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    @Query("SELECT c FROM Course c WHERE LOWER(c.title) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Course> findAllByTitle(@Param("keyword") String title);

    Optional<Course> findByTitle(String title);
    List<Course> findAll();

    @Query("SELECT c FROM Course c WHERE LOWER(c.teacher) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Course> findAllByTeacher(@Param("keyword") String teacher);
}
