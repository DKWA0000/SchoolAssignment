package com.school.schoolSystem.repository;

import com.school.schoolSystem.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepositoryOld {

    private List<Student> students;

    public StudentRepositoryOld(List<Student> students) {
        this.students = students;
    }

}
