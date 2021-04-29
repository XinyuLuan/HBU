package com.hbu.backend.model.dto;

import com.hbu.backend.model.utility.RoleType;
import com.hbu.backend.model.entity.course.CourseModule;

import java.util.List;

/**
 * What inputs are we receiving as an instructor?
 *  inputs:
 *      grading course documents
 *      comments for student assignments
 *      creating files, modules, etc.
 *
 **/
public class InstructorDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private RoleType roleType;
    private List<Long> courseModules;


    public InstructorDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

//    public List<Course> getCourses() {
//        return courses;
//    }

//    public void setCourses(List<Course> courses) {
//        this.courses = courses;


    public List<Long> getCourseModules() {
        return courseModules;
    }

    public void setCourseModules(List<Long> courseModules) {
        this.courseModules = courseModules;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }
}
