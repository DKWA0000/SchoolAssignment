package com.school.schoolSystem.repository;

import com.school.schoolSystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query("SELECT s FROM Student s")
    public List<Student> findAll();

    @Query("SELECT s FROM Student s WHERE lower(s.name) LIKE lower(concat('%', :keyword, '%'))")
    public List<Student> findByName(@Param("keyword") String name);

    @Query("SELECT s FROM Student  s WHERE s.age = :keyword")
    public List<Student> findByAge(@Param("keyword") int age);

    @Query("DELETE FROM Student  s WHERE s.id = :keyword")
    public boolean deleteStudent(@Param("keyword") int id);


}

