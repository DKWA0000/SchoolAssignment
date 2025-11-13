package com.school.schoolSystem.service;

import com.school.schoolSystem.dto.StudentResponseDTO;
import com.school.schoolSystem.model.Student;
import com.school.schoolSystem.repository.StudentRepository;
import com.school.schoolSystem.repository.StudentRepositoryOld;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {

    private StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
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

    public boolean deleteStudent(int id){
        return repo.deleteStudent(id);
    }

}