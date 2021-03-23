package com.hbu.backend.service;

import com.hbu.backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class StudentService {
    // contains: CourseService, MessageService, WebService?
    @Autowired
    StudentRepository studentRepository;

}
