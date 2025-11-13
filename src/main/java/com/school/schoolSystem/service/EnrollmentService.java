package com.school.schoolSystem.service;

import com.school.schoolSystem.dto.*;
import com.school.schoolSystem.model.Course;
import com.school.schoolSystem.model.Enrollment;
import com.school.schoolSystem.model.Student;
import com.school.schoolSystem.repository.CourseRepository;
import com.school.schoolSystem.repository.EnrollmentRepository;
import com.school.schoolSystem.repository.StudentRepository;
import org.springframework.stereotype.Service;

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

    public String gradeStudent(EnrollGradeDTO dto){
        Optional<Integer> tmp = repo.findEnrollment(dto.getStudentId(), dto.getCourseId());
        if(tmp.isPresent()) {
            repo.save(new Enrollment(tmp.get(),
                    repo.findById(tmp.get()).get().getStudentId(),
                    repo.findById(tmp.get()).get().getCourseId(),
                    dto.getGrade(), repo.findById(tmp.get()).get().isActive()));
            return "grade updated";
        }
        return "Please Enter a valid (studentId, courseId) combination";
    }

    public String addEnrollment(EnrollDTO dto){
        if(checkIfEnrollmentIsValid(dto)) {
            Optional<Student> studentTmp = studentRepository.findById(dto.getStudentId());
            Optional<Course> courseTmp = courseRepository.findById(dto.getCourseId());

            if (studentTmp.isPresent() && courseTmp.isPresent()) {
                repo.save(new Enrollment(studentTmp.get(), courseTmp.get()));
                return "Student enrolled in course: " + courseTmp.get().getTitle();
            }
        }
        return "Enrollment data is invalid";
    }

    public Optional<EnrolledStudentsDTO> getEnrolledStudents(int courseId){
        return courseRepository.findById(courseId)
                .map(course -> new EnrolledStudentsDTO(course.getTitle(),
                repo.getEnrollmentsByCourse(courseId).stream()
                .map(this::buildStudentDTO).toList()));
    }

    public StudentEnrollDTO buildStudentDTO(Enrollment enrollment){
         Student tmp = studentRepository.findById(enrollment.getStudentId().getId()).get();
         return new StudentEnrollDTO(tmp.getName(), tmp.getAge(), tmp.getEmail(), enrollment.getRegDate(),
                 enrollment.isActive(), enrollment.getGrade());
    }

    private boolean checkIfEnrollmentIsValid(EnrollDTO dto){
        return repo.findEnrollment(dto.getStudentId(), dto.getCourseId()).isEmpty() &&
               repo.nrEnrollmentsCourse(dto.getCourseId()).isEmpty();
    }
}
