package com.school.schoolSystem.controller;

import com.school.schoolSystem.dto.StudentRequestDTO;
import com.school.schoolSystem.dto.StudentResponseDTO;
import com.school.schoolSystem.model.Student;
import com.school.schoolSystem.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@Validated
public class StudentController {

    StudentService service;

    public StudentController(StudentService  service){
        this.service = service;
    }

    /*
    // Replaced by createStudent()
    @PostMapping("/add/{name}/{age}/{email}")
    public ResponseEntity<Boolean> addStudent(@PathVariable String name, @PathVariable String age, @PathVariable String email){
        long id = service.findAll().stream().count() + 1;
        Student student = new Student((int) id, name, Integer.parseInt(age), email);
        return ResponseEntity.ok(true);
    }
    */

    @GetMapping("/all")
    public List<StudentResponseDTO> getAllStudents() {
        return service.findAll();
    }

    @GetMapping("/get/name/{name}")
    public ResponseEntity<List<StudentResponseDTO>>getStudentsByName(@PathVariable String name){
        List<StudentResponseDTO> students = service.findByName(name);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/get/age/{age}")
    public ResponseEntity<List<StudentResponseDTO>> getStudentsByAge(@PathVariable String age){
        List<StudentResponseDTO> students = service.findByAge(Integer.parseInt(age));
        return ResponseEntity.ok(students);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStudent(@PathVariable String id){
        return ResponseEntity.ok(service.deleteStudent(Integer.parseInt(id)));
    }

    @PostMapping
    public ResponseEntity<StudentResponseDTO> createStudent(
            @RequestBody @Valid StudentRequestDTO studentDTO
    ) {
        StudentResponseDTO response = service.createStudent(studentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}