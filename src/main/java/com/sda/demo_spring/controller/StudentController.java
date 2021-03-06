package com.sda.demo_spring.controller;

import com.sda.demo_spring.model.Student;
import com.sda.demo_spring.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/student")
public class StudentController {

    // kontroler  zakaz bezpośredniego odwoływania się do bazy danych.
    private final StudentService studentService;
    private final Student defaultStudent;

    @Autowired // @RequiredArgsConstructor
    public StudentController(StudentService studentService, Student defaultMaxStudent) {
        this.studentService = studentService;
        this.defaultStudent = defaultMaxStudent;
    }

    // http://localhost:8080/student
    @GetMapping("")
    public String getStudents(Model model) {
        model.addAttribute("listOfStudents", studentService.findAll());
        return "student_list";
    }

    // ############ FORMULARZ
    @GetMapping("/form")
    public String getForm(Model model) { // ponieważ Student jest POJO, to stworzy to nową instancję i ją wstrzyknie
        model.addAttribute("addedStudent", defaultStudent);
//    @GetMapping("/form")
//    public String getForm(Model model) { // ponieważ Student jest POJO, to stworzy to nową instancję i ją wstrzyknie
//        model.addAttribute("addedStudent", new Student());
        return "student_form";
    }

    @PostMapping("")
    public String submitStudent(Student student) {
        studentService.save(student);
        return "redirect:/student";
    }


    // #######################################################################################
    // DO UZUPEŁNIENIA JUTRO / DLA CHĘNTYCH W DOMU (metoda delete i get by id - details)
    // http://localhost:8080/student/5
    @GetMapping("/{id}")
    public String getStudent(@PathVariable(name = "id") Long id) {
        return "student_details";
    }
}