package com.school.schoolSystem.service;

import com.school.schoolSystem.dto.EnrollDTO;
import com.school.schoolSystem.dto.EnrolledStudentsDTO;
import com.school.schoolSystem.dto.StudentEnrollDTO;
import com.school.schoolSystem.dto.StudentResponseDTO;
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

    public String addEnrollment(EnrollDTO dto){
        Optional<Student> studentTmp = studentRepository.findById(dto.getStudentId());
        Optional<Course> courseTmp = courseRepository.findById(dto.getCourseId());

        if(studentTmp.isPresent() && courseTmp.isPresent()){
            repo.save(new Enrollment(studentTmp.get(), courseTmp.get()));
            return "Student enrolled in course: " + courseTmp.get().getTitle();
        }
        return "student already registered in course: " + courseTmp.get().getTitle();
    }

    public Optional<EnrolledStudentsDTO> getEnrolledStudents(int courseId){
        Optional<Course> tmp = courseRepository.findById(courseId);
        if(tmp.isPresent()){
            List<Enrollment> enrollTmp = repo.findAll().stream()
                    .filter(enrollment -> enrollment.getCourseId().getId() == courseId).toList();
            List<StudentEnrollDTO> tmpList = enrollTmp.stream()
                    .map(this::buildStudentDTO).toList();
            return Optional.of(new EnrolledStudentsDTO(tmp.get().getTitle(), tmpList));
        }
        return Optional.empty();
    }

    public StudentEnrollDTO buildStudentDTO(Enrollment enrollment){
         Student tmp = studentRepository.findById(enrollment.getStudentId().getId()).get();
         return new StudentEnrollDTO(tmp.getName(), tmp.getAge(), tmp.getEmail(), enrollment.getRegDate());
    }
}
