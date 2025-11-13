package com.school.schoolSystem.controller;

import com.school.schoolSystem.dto.EnrollDTO;
import com.school.schoolSystem.dto.EnrollGradeDTO;
import com.school.schoolSystem.dto.EnrolledStudentsDTO;
import com.school.schoolSystem.model.Enrollment;
import com.school.schoolSystem.service.EnrollmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EnrollmentController {

    private final EnrollmentService service;

    public EnrollmentController(EnrollmentService service) {
        this.service = service;
    }

    @PostMapping("/enrollments/enroll")
    public ResponseEntity<String> EnrollInCourse(@RequestBody EnrollDTO dto){
        return ResponseEntity.ok(service.addEnrollment(dto));
    }

    @GetMapping("/enrollments/{courseId}")
    public ResponseEntity<EnrolledStudentsDTO> getEnrolledStudents(@PathVariable int courseId){
        return service.getEnrolledStudents(courseId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/enrollments/grade")
    public ResponseEntity<String> setGradeForEnrollment(@RequestBody EnrollGradeDTO dto){
        return ResponseEntity.ok(service.gradeStudent(dto));
    }
}
