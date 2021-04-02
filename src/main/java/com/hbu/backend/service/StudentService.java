package com.hbu.backend.service;

import com.hbu.backend.model.dto.StudentScriptDTO;
import com.hbu.backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class StudentService {
    // contains: CourseService, MessageService, WebService?
    @Autowired
    StudentRepository studentRepository;

    public StudentScriptDTO getUnofficalTrscipt(String studentId, int sid){
        /*
        ....
         */
        return new StudentScriptDTO();
    }

}
