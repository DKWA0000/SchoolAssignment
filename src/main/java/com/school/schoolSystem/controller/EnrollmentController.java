package com.school.schoolSystem.controller;

import com.school.schoolSystem.dto.EnrollDTO;
import com.school.schoolSystem.dto.EnrollGradeDTO;
import com.school.schoolSystem.dto.EnrolledStudentsDTO;
import com.school.schoolSystem.model.Enrollment;
import com.school.schoolSystem.service.EnrollmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    @PutMapping("/enrollments/disEnroll")
    public ResponseEntity<String> disEnrollCourse(@RequestBody EnrollDTO dto){
        return ResponseEntity.ok(service.disEnrollCourse(dto));
    }

    @GetMapping("/enrollments/getByGrade/{grade}")
    public ResponseEntity<List<Optional<EnrolledStudentsDTO>>> getEnrolledByGrade(@PathVariable int grade){
        return ResponseEntity.ok(service.getAllEnrolledByGrade(grade));
    }

    @GetMapping("/enrollments/summary")
    public ResponseEntity<List<Optional<EnrolledStudentsDTO>>> getEnrolledByGrade(){
        return ResponseEntity.ok(service.getAllEnrolledByGrade(-2));
    }

    @GetMapping("/enrollments/getByDate/{date}")
    public ResponseEntity<List<Optional<EnrolledStudentsDTO>>> getEnrollmentsByDate(String date){
        return ResponseEntity.ok(service.getAllEnrolledByDate(date));
    }
}
