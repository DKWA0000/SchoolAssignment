package com.school.schoolSystem.controller;

import com.school.schoolSystem.service.EnrollmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EnrollmentController {

    private final EnrollmentService service;

    public EnrollmentController(EnrollmentService service) {
        this.service = service;
    }

    @GetMapping("/enrollments/{courseId}")
    public ResponseEntity<List<Integer>> getEnrolledStudents(@PathVariable int courseId){
        return ResponseEntity.ok(service.getEnrolledStudents(courseId));
    }

}
