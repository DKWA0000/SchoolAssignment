package com.school.schoolSystem.service;

import com.school.schoolSystem.dto.StudentRequestDTO;
import com.school.schoolSystem.dto.StudentResponseDTO;
import com.school.schoolSystem.model.Student;
import com.school.schoolSystem.repository.EnrollmentRepository;
import com.school.schoolSystem.repository.StudentRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {

    private StudentRepository repo;
    private EnrollmentRepository enrollmentRepo;

    public StudentService(StudentRepository repo, EnrollmentRepository enrollmentRepo) {
        this.repo = repo;
        this.enrollmentRepo = enrollmentRepo;
    }

    public List<StudentResponseDTO> findAll() {
        return repo.findAll().stream().map(s -> new StudentResponseDTO(
                s.getId(),
                s.getName(),
                s.getAge(),
                s.getEmail())).toList();

    }

    public List<StudentResponseDTO> findByName(String name){
        return repo.findByName(name).stream().map(s -> new StudentResponseDTO(
                s.getId(),
                s.getName(),
                s.getAge(),
                s.getEmail()
        )).toList();

    }

    public List<StudentResponseDTO> findByAge(int age){
        return repo.findByAge(age).stream().map(s -> new StudentResponseDTO(
                s.getId(),
                s.getName(),
                s.getAge(),
                s.getEmail()
        )).toList();
    }

    @Transactional
    public boolean deleteStudent(int id){
        enrollmentRepo.deleteEnrollmentsWhereStudentId(id);
        repo.deleteStudent(id);
        return true;
    }

    public StudentResponseDTO createStudent(StudentRequestDTO dto) {
        repo.findByEmail(dto.getEmail())
                .ifPresent(c -> {
                    throw new EntityExistsException("Student with the given email already exists");
                });

        Student student = new Student(dto.getName(), dto.getAge(), dto.getEmail());
        //table students
        Student savedStudent = repo.save(student);

        StudentResponseDTO response = new StudentResponseDTO(student.getId(), student.getName(), student.getAge(), student.getEmail());
        return response;

    }
}