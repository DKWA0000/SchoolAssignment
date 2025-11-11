package com.school.schoolSystem.service;

import com.school.schoolSystem.dto.CourseRequestDTO;
import com.school.schoolSystem.dto.CourseResponseDTO;
import com.school.schoolSystem.exception.CourseAlreadyExistsException;
import com.school.schoolSystem.exception.CourseNotFoundException;
import com.school.schoolSystem.exception.InvalidFieldValueException;
import com.school.schoolSystem.model.Course;
import com.school.schoolSystem.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private CourseRepository repository;

    public CourseService(CourseRepository repo) {
        this.repository = repo;
    }

    public CourseResponseDTO createCourse(CourseRequestDTO courseRequest) {

        repository.findByTitle(courseRequest.getTitle())
            .ifPresent(c -> {
                throw new CourseAlreadyExistsException(
                        "Course with title: " + courseRequest.getTitle() + " already exists"
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
                .active(true)
                .build();

        Course saved = repository.save(course);

        return CourseResponseDTO.builder()
                .id(saved.getId())
                .title(saved.getTitle())
                .teacher(saved.getTeacher())
                .maxStudents(saved.getMaxStudents())
                .active(saved.isActive())
                .build();
    }

    public List<CourseResponseDTO> findAll() {
        List<Course> all = repository.findAll();
        List<CourseResponseDTO> response = new ArrayList<>();
        for(Course entity : all) {
            response.add(CourseResponseDTO.builder()
                    .id(entity.getId())
                    .title(entity.getTitle())
                    .teacher(entity.getTeacher())
                    .maxStudents(entity.getMaxStudents())
                    .active(entity.isActive())
                    .build());
        }
        return response;
    }

    public List<CourseResponseDTO> findAllActiveCourses() {
        List<Course> all = repository.findAll();
        List<CourseResponseDTO> response = new ArrayList<>();

        for(Course entity : all) {
            if(entity.isActive()) {
                response.add(CourseResponseDTO.builder()
                        .id(entity.getId())
                        .title(entity.getTitle())
                        .teacher(entity.getTeacher())
                        .maxStudents(entity.getMaxStudents())
                        .active(entity.isActive())
                        .build());
            }
        }
        return response;
    }

    public CourseResponseDTO findCourseById(Integer id) {
        Optional<Course> foundCourse = repository.findById(id);

        if(foundCourse.isEmpty()) {
            throw new CourseNotFoundException("Course with id: " + id + " does not exists");
        }

        return CourseResponseDTO.builder()
                .id(foundCourse.get().getId())
                .title(foundCourse.get().getTitle())
                .teacher(foundCourse.get().getTeacher())
                .maxStudents(foundCourse.get().getMaxStudents())
                .build();
    }


    public boolean deleteCourseById(Integer id) {
        /*todo*/
        Optional<Course> foundCourse = repository.findById(id); //
        if(foundCourse.isEmpty()) {
            throw new CourseNotFoundException("Course with id: " + id + " does not exists");
        } else if(!foundCourse.get().isActive()){
            throw new CourseAlreadyExistsException("Course with id: " + id + " has been already removed from db");
        }
        repository.delete(foundCourse.get());
        return true;
    }

    public CourseResponseDTO findCourseByTitle(String title) {

        Optional<Course> foundCourse = repository.findByTitle(title);
        if(foundCourse.isEmpty()) {
            throw new CourseNotFoundException("Course with title: " + title + " does not exists");
        }

        return CourseResponseDTO.builder()
                .id(foundCourse.get().getId())
                .title(foundCourse.get().getTitle())
                .teacher(foundCourse.get().getTeacher())
                .maxStudents(foundCourse.get().getMaxStudents())
                .build();
    }

    public List<CourseResponseDTO> findCourseByTeacher( String teacher) {
        List<Course> allByTeacher = repository.findAllByTeacher(teacher);
        List<CourseResponseDTO> response = new ArrayList<>();

        for(Course entity : allByTeacher) {
            if(entity.isActive()) {
                response.add(CourseResponseDTO.builder()
                        .id(entity.getId())
                        .title(entity.getTitle())
                        .teacher(entity.getTeacher())
                        .maxStudents(entity.getMaxStudents())
                        .active(entity.isActive())
                        .build());
            }
        }
        return response;
    }

}
