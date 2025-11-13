package com.school.schoolSystem.repository;

import com.school.schoolSystem.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.NativeQuery;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {

    @NativeQuery(value = "SELECT enrollment_id FROM enrollments" +
                            " WHERE (student_id = ?1) AND (course_id = ?2)")
    Optional<Integer> findEnrollment(int studentId, int courseId);

    @NativeQuery(value = "SELECT (COUNT(course_id) >= max_students) FROM enrollments " +
                                "JOIN courses ON (course_id = id) " +
                                "WHERE course_id = ?1")
    Optional<Integer> nrEnrollmentsCourse(int courseId);

    @NativeQuery("SELECT * FROM enrollments " +
                    "WHERE (course_id = ?1)")
    List<Enrollment> getEnrollmentsByCourse(int courseId);

    @Modifying
    @NativeQuery("DELETE FROM enrollments " +
            "WHERE (student_id = ?1)")
    void deleteEnrollmentsWhereStudentId(int studentId);
}
