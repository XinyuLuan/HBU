package com.hbu.backend.controller;

import com.hbu.backend.model.entity.Course;
import com.hbu.backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StudentController {

    private final static String TAG_ = "StudentController";

    // has a CourseController
    @Autowired
    StudentService studentService;

    // add course
    public boolean addCourse(long courseID){
        // verify course id
        // if course id exists in database,
        // then check the requirements for adding a class (make sure condition and date range is not violated)
        return false;
    }

    // drop class
    public boolean dropCourse(long courseID){
        // similar logic compared to addCourse method
        return false;
    }

    // update course (has the ability to add or drop course)
    public boolean updateCourse(long courseID, int actionRequest){
        // verify course id
        // check if the action request is permitted
        return false;
    }

    // view schedule of courses
    public List<Course> getScheduleOfCourses(long studentID){
        // if student id is valid, then return their schedule
        return null;
    }

    // view course search page
    public List<Course> getAvailableCourses(){
        // use course controller to access all available courses for the semester
        return null;
    }

    // view class registration page (does this use a signup controller?)

    // update student profile
    public boolean updateProfile(long studentID){

        return false;
    }

    // login to dashboard

    // view course grades

    // order official transcript

    // complete survey questionnaire

}
