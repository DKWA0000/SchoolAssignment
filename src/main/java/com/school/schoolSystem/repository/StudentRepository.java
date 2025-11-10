package com.school.schoolSystem.repository;

import com.school.schoolSystem.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository {

    private List<Student> students;

    public StudentRepository(List<Student> students) {
        this.students = students;
    }

}
