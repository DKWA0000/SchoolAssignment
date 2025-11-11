package com.school.schoolSystem.service;

import com.school.schoolSystem.dto.EnrollDTO;
import com.school.schoolSystem.model.Course;
import com.school.schoolSystem.model.Enrollment;
import com.school.schoolSystem.model.Student;
import com.school.schoolSystem.repository.CourseRepository;
import com.school.schoolSystem.repository.EnrollmentRepository;
import com.school.schoolSystem.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentService {

    private final EnrollmentRepository repo;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public EnrollmentService(EnrollmentRepository repo,  CourseRepository courseRepository,   StudentRepository studentRepository) {
        this.repo = repo;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    public int addEnrollment(EnrollDTO dto){
//        if(!repo.checkIfStudentRegistered(dto)){
//            repo.addEnrollment(dto);
//            return 1;
//        }
        return 2;
    }

    public int deleteEnrollment(EnrollDTO dto){
//        if(!repo.checkIfStudentRegistered(dto)){
//            repo.makeEnrollmentInactive(dto);
//            return 1;
//        }
        return 2;
    }

    public Enrollment getEnrolledStudents(int courseId){
        Student student = new Student("name", 12, "email@em.com");
        Student save1 = studentRepository.save(student);
        Optional<Course> byId = courseRepository.findById(courseId);
        Enrollment save = new Enrollment();
        if(byId.isPresent()){
            save = repo.save(new Enrollment(save1, byId.get()));
            System.out.println(save);
        }

        return save;
    }
}
