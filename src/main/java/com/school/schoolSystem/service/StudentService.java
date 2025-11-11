package com.school.schoolSystem.service;

import com.school.schoolSystem.dto.StudentResponseDTO;
import com.school.schoolSystem.repository.StudentRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {

    private StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    public List<StudentResponseDTO> findAll() {
        return repo.findAll();
    }

    public List<StudentResponseDTO> findByName(String name){
        return repo.findByName(name);
    }

    public List<StudentResponseDTO> findByAge(int age){
        return repo.findByAge(age);
    }

    public boolean deleteStudent(int id){
        return repo.deleteStudent(id);
    }

}