package com.school.schoolSystem.service;

import com.school.schoolSystem.dto.*;
import com.school.schoolSystem.model.Course;
import com.school.schoolSystem.model.Enrollment;
import com.school.schoolSystem.model.Student;
import com.school.schoolSystem.repository.CourseRepository;
import com.school.schoolSystem.repository.EnrollmentRepository;
import com.school.schoolSystem.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public String disEnrollCourse(EnrollDTO dto){
        if(repo.findEnrollment(dto.getStudentId(), dto.getCourseId()).isPresent()) {
            int tmp = repo.findEnrollment(dto.getStudentId(), dto.getCourseId()).get();
            repo.save(new Enrollment(tmp, repo.findById(tmp).get().getStudentId(),
                    repo.findById(tmp).get().getCourseId(),
                    repo.findById(tmp).get().getGrade(), false));
            return "Student has been dis-enrolled";
        }
        return "enrollment is not active";
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

    public List<Optional<EnrolledStudentsDTO>> getAllEnrolledByGrade(int grade){
        List<Integer> courses = repo.getAllCourseIds();
        List<Optional<EnrolledStudentsDTO>> tmp = new ArrayList<>();
        for(int i = 0; i < courses.size(); i++){
            tmp.add(getEnrolledByGrade(courses.get(i), grade));
            System.out.println(tmp.size());
        }
        return tmp;
    }

    public List<Optional<EnrolledStudentsDTO>> getAllEnrolledByDate(String date){
        List<Integer> courses = repo.getAllCourseIds();
        List<Optional<EnrolledStudentsDTO>> tmp = new ArrayList<>();
        for(int i = 0; i < courses.size(); i++){
            tmp.add(getEnrolledByDate(courses.get(i), date));
            System.out.println(tmp.size());
        }
        return tmp;
    }

    public Optional<EnrolledStudentsDTO> getEnrolledByGrade(int courseId, int grade){
        return courseRepository.findById(courseId)
                .map(course -> new EnrolledStudentsDTO(course.getTitle(),
                 repo.getEnrollmentsByGrade(courseId, grade).stream()
                 .map(this::buildStudentDTO).toList()));
    }

    public Optional<EnrolledStudentsDTO> getEnrolledStudents(int courseId){
        return courseRepository.findById(courseId)
                .map(course -> new EnrolledStudentsDTO(course.getTitle(),
                repo.getEnrollmentsByCourse(courseId).stream()
                .map(this::buildStudentDTO).toList()));
    }

    public Optional<EnrolledStudentsDTO> getEnrolledByDate(int courseId, String date){
        return courseRepository.findById(courseId)
                .map(course -> new EnrolledStudentsDTO(course.getTitle(),
                        repo.getEnrollmentsByDate(date, courseId).stream()
                                .map(this::buildStudentDTO).toList()));
    }

    public StudentEnrollDTO buildStudentDTO(Enrollment enrollment){
         Student tmp = studentRepository.findById(enrollment.getStudentId().getId()).get();
         return new StudentEnrollDTO(tmp.getName(), tmp.getAge(), tmp.getEmail(), enrollment.getRegDate(),
                 enrollment.isActive(), enrollment.getGrade());
    }

    private boolean checkIfEnrollmentIsValid(EnrollDTO dto){
        return  repo.findEnrollment(dto.getStudentId(), dto.getCourseId()).isEmpty() &&
                (((repo.nrEnrollmentsCourse(dto.getCourseId()).isEmpty()) ||
                    (repo.nrEnrollmentsCourse(dto.getCourseId()).get() == 1) ||
                    (repo.checkCourseStatus(dto.getCourseId()).get() == 0)) &&
                (courseRepository.findById(dto.getCourseId()).isPresent() &&
                    (studentRepository.findById(dto.getStudentId()).isPresent())));
    }
}
