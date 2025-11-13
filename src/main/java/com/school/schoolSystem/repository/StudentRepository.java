package com.school.schoolSystem.repository;

import com.school.schoolSystem.model.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query("SELECT s FROM Student s")
    public List<Student> findAll();

    @Query("SELECT s FROM Student s WHERE lower(s.name) LIKE lower(concat('%', :keyword, '%'))")
    public List<Student> findByName(@Param("keyword") String name);

    @Query("SELECT s FROM Student  s WHERE s.age = :keyword")
    public List<Student> findByAge(@Param("keyword") int age);

    @Modifying
    @Query("DELETE FROM Student  s WHERE s.id = :keyword")
    void deleteStudent(@Param("keyword") int id);

    Optional<Student> findByEmail(String email);
}

