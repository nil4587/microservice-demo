package com.nileshprajapati.webservicedemo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(path = "all")
    public List<Student> getStudents() {
        return studentService.getAllStudentsFromRepository();
    }

    @PostMapping(path = "registration")
    public void registerNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "delete/{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long id) {
        studentService.deleteStudentBy(id);
    }

    @PutMapping(path = "update/{studentId}")
    public void updateStudent(@PathVariable("studentId") Long id,
                              @RequestParam String name,
                              @RequestParam String email) {
        studentService.updateStudent(id, name, email);
    }
}
