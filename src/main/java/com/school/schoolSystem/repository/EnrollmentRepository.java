package com.school.schoolSystem.repository;

import com.school.schoolSystem.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.NativeQuery;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {

    @NativeQuery("SELECT enrollment_id FROM enrollments " +
                      "JOIN courses ON (course_id = id)" +
                      " WHERE (student_id = ?1) AND (course_id = ?2) " +
                      "AND (enrollments.active = 1)" +
                      "LIMIT 1")
    Optional<Integer> findEnrollment(int studentId, int courseId);


    @NativeQuery(value = "SELECT (COUNT(course_id) < max_students) FROM enrollments " +
                                "JOIN courses ON (course_id = id) " +
                                "WHERE course_id = ?1")
    Optional<Integer> nrEnrollmentsCourse(int courseId);

    @NativeQuery("SELECT * FROM enrollments " +
                    "WHERE (course_id = ?1)")
    List<Enrollment> getEnrollmentsByCourse(int courseId);

    @NativeQuery("SELECT id FROM courses")
    List<Integer> getAllCourseIds();

    @NativeQuery("SELECT * FROM enrollments " +
            "WHERE (course_id = ?1) AND (?2 <= course_grade)")
    List<Enrollment> getEnrollmentsByGrade(int courseId, int grade);

    @NativeQuery(value = "SELECT EXISTS(SELECT * FROM enrollments " +
                         "JOIN courses ON (course_id = id) " +
                         "WHERE (courses.active = 1) AND " +
                         "(id = ?1));")
    Optional<Integer> checkCourseStatus(int course);

    @NativeQuery("SELECT * FROM enrollments " +
                   "WHERE (reg_date > ?1)")
    List<Enrollment> getEnrollmentsByDate(String date);

    @Modifying
    @NativeQuery("DELETE FROM enrollments " +
            "WHERE (student_id = ?1)")
    void deleteEnrollmentsWhereStudentId(int studentId);
}
