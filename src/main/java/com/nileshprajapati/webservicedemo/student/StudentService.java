package com.nileshprajapati.webservicedemo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return List.of(
                new Student(
                        "Jack",
                        "jack.daniel@gmail.com",
                        LocalDate.of(2007, Month.APRIL, 01)
                )
        );
    }

    public List<Student> getAllStudentsFromRepository() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
       Optional<Student> studentIfAvailable = studentRepository.findStudentByEmail(student.getEmail());
        if (studentIfAvailable.isPresent()){
            throw new IllegalArgumentException("Student with email " + student.getEmail() + " already exists");
        } else {
            studentRepository.save(student);
        }
        System.out.println(student);
    }

    public void deleteStudentBy(Long id) {
        boolean exist = studentRepository.existsById(id);
        if (!exist) {
            throw new IllegalArgumentException("Student with id " + id + " does not exist");
        } else {
            studentRepository.deleteById(id);
        }
    }

    @Transactional
    public void updateStudent(Long id, String name, String email) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student with id " + id + " does not exist"));
        if (name != null &&
                !name.isEmpty() &&
                !Objects.equals(name, student.getName())) {
            student.setName(name);
        }

        if (email != null &&
                !email.isEmpty() &&
                !Objects.equals(email, student.getEmail())) {
            Optional<Student> studentIfAvailable = studentRepository.findStudentByEmail(email);
            if (studentIfAvailable.isPresent()) {
                throw new IllegalArgumentException("Student with email " + email + " already exists");
            }
            student.setEmail(email);
        }
    }
}
