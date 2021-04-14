package com.hbu.backend.model.entity.course;

import java.io.File;
import java.util.List;

public class Submission {
    // view for student and instructor is different
    // should create two entity for one submission or just change the view

//    List<Component> components;

    // controller:
    // instructor addcomponent(RequestPama

    /**
     * Components:
     *      fileSubmission
     *      plainTextSubmission
     *      MultipleSubmission
     *


    /**
     * Subclass: (different format of submission)
     *      syllabus
     *      announcement
     *      assignment
     *      quiz
     *      grades
     *      courseInfo
     *      discussion board
     *      other
     */


    private Long id;
    private String title;
    private String content;
    private List<File> files;

}
