package com.school.schoolSystem.controller;

import com.school.schoolSystem.dto.StudentResponseDTO;
import com.school.schoolSystem.model.Student;
import com.school.schoolSystem.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    StudentService service;

    public StudentController(StudentService  service){
        this.service = service;
    }

    @PostMapping("/add/{name}/{age}/{email}")
    public ResponseEntity<Boolean> addStudent(@PathVariable String name, @PathVariable String age, @PathVariable String email){
        long id = service.findAll().stream().count() + 1;
        Student student = new Student((int) id, name, Integer.parseInt(age), email);
        return ResponseEntity.ok(true);
    }

    @GetMapping("/all")
    public List<StudentResponseDTO> getAllStudents() {
        return service.findAll();
    }

   /* @GetMapping("/get/name/{name}")
    public ResponseEntity<StudentResponseDTO>getStudentsByName(@PathVariable String name){
        StudentResponseDTO student = service.findByName(name).getFirst();
        return ResponseEntity.ok(student);
    }

    @GetMapping("/get/age/{age}")
    public ResponseEntity<StudentResponseDTO> getStudentsByAge(@PathVariable String age){
        StudentResponseDTO student = service.findByAge(Integer.parseInt(age)).getFirst();
        return ResponseEntity.ok(student);
    }*/

    @DeleteMapping("/delete/{id}")
    public boolean deleteStudent(@PathVariable String id){
        return service.deleteStudent(Integer.parseInt(id));
    }

}