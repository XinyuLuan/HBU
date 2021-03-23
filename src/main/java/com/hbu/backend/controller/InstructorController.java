package com.hbu.backend.controller;

import com.hbu.backend.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * What inputs are we receiving as an instructor?
 *  inputs:
 *      grading course documents
 *      comments for student assignments
 *      creating files, modules, etc.
 *
 **/
@RestController
@RequestMapping("/instructor")
public class InstructorController {

    @Autowired
    InstructorService instructorService;

    // add class & drop class functionalities reserved for admin only?

    // view course schedule

    // get all instructed courses

    // view class registration page

    // update changes to class schedule

    // update credentials

    // login to dashboard

    // view course roster grades

    // grade document

    // add feedback notes to documents

    // create document

    // create module

    // create media (multimedia)
}
