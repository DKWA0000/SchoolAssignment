package com.school.schoolSystem.repository;

import com.school.schoolSystem.dto.CourseResponseDTO;
import com.school.schoolSystem.exception.CourseNotFoundException;
import com.school.schoolSystem.model.Course;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class CourseRepository {

    private List<Course> courses;
    private final AtomicInteger idCounter = new AtomicInteger(1);

    public CourseRepository(List<Course> courses) {
        this.courses = courses;
    }

    public Optional<Course> findById(int id) {
        return courses.stream().filter(c -> c.getId() == id && c.isActive()).findFirst();
    }

    public Optional<Course> findByOnlyId(int id) {
        return courses.stream().filter(c -> c.getId() == id).findFirst();
    }

    public Course save(Course course) {
        course.setId(idCounter.getAndIncrement());
        boolean added = courses.add(course);
        return course;
    }

    public List<CourseResponseDTO> findAll() {
        return courses.stream()
                .map(c -> new CourseResponseDTO(
                        c.getId(),
                        c.getTitle(),
                        c.getTeacher(),
                        c.getMaxStudents(),
                        c.getStudents(),
                        c.isActive()))
                .collect(Collectors.toUnmodifiableList());
    }

    public boolean delete(Integer id) {
        Boolean b1 = courses.stream().filter(c -> c.getId() == id).findFirst().map(u -> {
            u.setActive(false);
            return u.isActive();
        }).orElseThrow();
        return !b1;
    }

    public Optional<Course> findByTitle(String title) {
        Optional<Course> first = courses.stream().filter(c -> c.getTitle().equals(title) && c.isActive()).findFirst();
        return first;
    }

    public List<CourseResponseDTO>  findAllByTeacher(String teacher) {
        List<Course> collect = courses.stream()
                .filter(c -> c.getTeacher().equals(teacher) && c.isActive())
                .collect(Collectors.toUnmodifiableList());

        return collect.stream()
                .map(c -> new CourseResponseDTO(
                        c.getId(),
                        c.getTitle(),
                        c.getTeacher(),
                        c.getMaxStudents(),
                        c.getStudents(),
                        c.isActive()))
                .collect(Collectors.toUnmodifiableList());
    }

}
