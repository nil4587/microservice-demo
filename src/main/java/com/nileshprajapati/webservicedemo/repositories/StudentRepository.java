package com.nileshprajapati.webservicedemo.repositories;

import com.nileshprajapati.webservicedemo.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // select * from student where email = ?;
    @Query("select s from Student s where s.email = ?1")
    Optional<Student> findStudentByEmail(String email);
}
