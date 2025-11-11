package com.school.schoolSystem.repository;

import com.school.schoolSystem.dto.StudentResponseDTO;
import com.school.schoolSystem.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository {

    private List<Student> students;

    public StudentRepository(List<Student> students) {
        this.students = students;
    }

    public List<StudentResponseDTO> findAll(){
        return students.stream().map(
                s -> new StudentResponseDTO(
                        s.getId(),       /*ID*/
                        s.getName(),     /*name*/
                        s.getAge(),      /*age*/
                        s.getEmail()     /*email*/
                )
        ).toList();
    }

    public List<StudentResponseDTO> findByName(String name){
        return students.stream().filter(s -> s.equals(name)).map(s ->
                new StudentResponseDTO(
                    s.getId(),
                    s.getName(),
                    s.getAge(),
                    s.getEmail()
                )).toList();
    }

    public List<StudentResponseDTO> findByAge(int age){
        return students.stream().filter(s -> s.getAge() == age).map(s ->
                new StudentResponseDTO(
                    s.getId(),
                    s.getName(),
                    s.getAge(),
                    s.getEmail()
                )).toList();
    }

    public boolean deleteStudent(int id){
        long n = students.stream().filter(s -> s.getId() == id).count();
        students = students.stream().filter(s -> s.getId() != id).toList();
        return n > 0;
    }


}
