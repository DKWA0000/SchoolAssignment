package com.school.schoolSystem.service;

import com.school.schoolSystem.dto.CourseRequestDTO;
import com.school.schoolSystem.dto.CourseResponseDTO;
import com.school.schoolSystem.exception.CourseAlreadyExistsException;
import com.school.schoolSystem.exception.CourseNotFoundException;
import com.school.schoolSystem.exception.InvalidFieldValueException;
import com.school.schoolSystem.model.Course;
import com.school.schoolSystem.repository.CourseRepository;
import io.micrometer.observation.ObservationFilter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private CourseRepository repo;

    public CourseService(CourseRepository repo) {
        this.repo = repo;
    }

    public CourseResponseDTO createCourse(CourseRequestDTO courseRequest) {

        repo.findByTitle(courseRequest.getTitle())
            .ifPresent(c -> {
                throw new CourseAlreadyExistsException(
                        "Course with title '" + courseRequest.getTitle() + "' already exists"
                );
            });

        int maxStudentsParsed;
        try {
            maxStudentsParsed = Integer.parseInt(courseRequest.getMaxStudents());
        } catch (NumberFormatException ex) {
            throw new InvalidFieldValueException("maxStudents must be a number");
        }

        Course course = Course.builder()
                .title(courseRequest.getTitle())
                .teacher(courseRequest.getTeacher())
                .maxStudents(maxStudentsParsed)
                .students(List.of())
                .build();

        Course saved = repo.save(course);

        return CourseResponseDTO.builder()
                .id(saved.getId())
                .title(saved.getTitle())
                .teacher(saved.getTeacher())
                .maxStudents(saved.getMaxStudents())
                .students(saved.getStudents())
                .build();

    }

    public List<CourseResponseDTO> findAll() {
        return repo.findAll();
    }

    public CourseResponseDTO findCourseById(Integer id) {
        Optional<Course> foundCourse = repo.findById(id);

        if(foundCourse.isEmpty()) {
            throw new CourseNotFoundException("Course with id '" + id + "' does not exists");
        }

        return CourseResponseDTO.builder()
                .id(foundCourse.get().getId())
                .title(foundCourse.get().getTitle())
                .teacher(foundCourse.get().getTeacher())
                .maxStudents(foundCourse.get().getMaxStudents())
                .students(foundCourse.get().getStudents())
                .build();
    }


    public boolean deleteCourseById(Integer id) {
        return repo.delete(id);
    }

    public CourseResponseDTO findCourseByTitle(String title) {

        Optional<Course> foundCourse = repo.findByTitle(title);
        if(foundCourse.isEmpty()) {
            throw new CourseNotFoundException("Course with title '" + title + "' does not exists");
        }

        return CourseResponseDTO.builder()
                .id(foundCourse.get().getId())
                .title(foundCourse.get().getTitle())
                .teacher(foundCourse.get().getTeacher())
                .maxStudents(foundCourse.get().getMaxStudents())
                .students(foundCourse.get().getStudents())
                .build();
    }

    public List<CourseResponseDTO> findCourseByTeacher( String teacher) {
        return repo.findAllByTeacher(teacher);
    }

}
