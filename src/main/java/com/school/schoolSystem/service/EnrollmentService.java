package com.school.schoolSystem.service;

import com.school.schoolSystem.dto.EnrollDTO;
import com.school.schoolSystem.repository.EnrollmentRepositoryOld;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {

    private final EnrollmentRepositoryOld repo;

    public EnrollmentService(EnrollmentRepositoryOld repo) {
        this.repo = repo;
    }

    public int addEnrollment(EnrollDTO dto){
        if(!repo.checkIfStudentRegistered(dto)){
            repo.addEnrollment(dto);
            return 1;
        }
        return 2;
    }

    public int deleteEnrollment(EnrollDTO dto){
        if(!repo.checkIfStudentRegistered(dto)){
            repo.makeEnrollmentInactive(dto);
            return 1;
        }
        return 2;
    }

    public List<Integer> getEnrolledStudents(int courseId){
        return repo.getEnrolledStudents(courseId);
    }
}
