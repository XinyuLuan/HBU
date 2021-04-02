package com.hbu.backend.model.dto;

import com.hbu.backend.model.entity.Course;

import java.util.Set;

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
    private Set<Course> courses;

    public InstructorDTO(long id, String firstName, String lastName, String email, Set<Course> courses) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.courses = courses;
    }

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

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
