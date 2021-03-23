package com.hbu.backend.model.dto;

import com.hbu.backend.model.entity.Course;

import java.util.List;

public class StudentDTO {
    // what about the university id? gpa info?
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<Course> courses;

    public StudentDTO() {
    }

    public StudentDTO(long id, String firstName, String lastName, String email, List<Course> courses) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.courses = courses;
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

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
