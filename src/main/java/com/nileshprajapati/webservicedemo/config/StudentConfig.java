package com.nileshprajapati.webservicedemo.config;

import com.nileshprajapati.webservicedemo.models.Student;
import com.nileshprajapati.webservicedemo.repositories.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student jack = new Student(
                    "Jack",
                    "jack.daniel@gmail.com",
                    LocalDate.of(2007, APRIL, 1)
            );

            Student jeal = new Student(
                    "Jeal",
                    "jeal.daniel@gmail.com",
                    LocalDate.of(2006, APRIL, 6)
            );

            studentRepository.saveAll(
                    List.of(jack, jeal)
            );
        };
    }
}
