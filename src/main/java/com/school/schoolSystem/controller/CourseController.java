package com.school.schoolSystem.controller;

import com.school.schoolSystem.dto.CoursePatchRequestDTO;
import com.school.schoolSystem.dto.CourseRequestDTO;
import com.school.schoolSystem.dto.CourseResponseDTO;
import com.school.schoolSystem.service.CourseService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
@Validated
public class CourseController {
    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<CourseResponseDTO> createCourse(
            @RequestBody @Valid CourseRequestDTO courseDTO
    ) {
        CourseResponseDTO response = courseService.createCourse(courseDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public List<CourseResponseDTO> getAllCourses() {
        return courseService.findAll();
    }

    @GetMapping("/active")
    public List<CourseResponseDTO> getAllActiveCourses() {
        return courseService.findAllActiveCourses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> getCourseById(
            @PathVariable
            @Min(value = 1, message = "ID must be >= 1")
            Integer id
    ) {
        CourseResponseDTO response = courseService.findCourseById(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCourseById(
            @PathVariable
            @Min(value = 1, message = "ID must be >= 1")
            Integer id
    ){
        boolean response = courseService.deleteCourseById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<CourseResponseDTO> getCourseByTitle(
            @RequestParam
            @NotBlank(message = "Title is mandatory")
            String title
    ) {
        CourseResponseDTO response = courseService.findCourseByTitle(title);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/searchByTeacher")
    public ResponseEntity<List<CourseResponseDTO>> getCourseByTeacher(
            @RequestParam
            @NotBlank(message = "Teacher is mandatory")
            String teacher
    ) {
        List<CourseResponseDTO> response = courseService.findCourseByTeacher(teacher);
        return ResponseEntity.ok(response);
    }


    @PatchMapping ("/{id}")
    public ResponseEntity<CourseResponseDTO>patchCourseById(@PathVariable int id,
                                                             @RequestBody CoursePatchRequestDTO coursePatchRequestDTO){
        CourseResponseDTO response = courseService.patchCourse(id, coursePatchRequestDTO);
        return response !=null ?
                ResponseEntity.ok(response) :
                ResponseEntity.notFound().build();

    }

    @PatchMapping ("/param/{id}")
    public ResponseEntity<CourseResponseDTO>patchCourseByIdParam
            (@PathVariable int id,
             @RequestParam(required = false) String title,
             @RequestParam(required = false) String teacher,
             @RequestParam(required = false) String maxStudentNum) {
        CoursePatchRequestDTO coursePatchRequestDTO = new CoursePatchRequestDTO(title, teacher,maxStudentNum);
        CourseResponseDTO response = courseService.patchCourse(id, coursePatchRequestDTO);
        return response !=null ?
                ResponseEntity.ok(response) :
                ResponseEntity.notFound().build();
    }




}

