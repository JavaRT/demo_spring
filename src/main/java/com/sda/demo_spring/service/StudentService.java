package com.sda.demo_spring.service;

import com.sda.demo_spring.model.Student;
import com.sda.demo_spring.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Bean to jest instancja klasy która jest singletonem w naszym projekcie. Instancja tworzona jest przy starcie aplikacji lub w dowolnym momencie działąnia.
//  - na bean składa się: instancja + jej typ + jej nazwa
@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Optional<Student> find(Long id) {
        return studentRepository.findById(id);
    }

    public void save(Student student) {
        studentRepository.save(student);
    }

    public void delete(Long id){
        studentRepository.deleteById(id);
    }
}