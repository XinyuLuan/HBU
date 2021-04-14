package com.hbu.backend.model.dto;

import com.hbu.backend.model.entity.course.Course;

import java.util.List;

public class StudentDTO {
    // what about gpa info?
    private long id;
    private String studentId;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private List<Course> courses;
    private String startTime;
    private String endTime;
    private boolean graduated;


    public StudentDTO() {
    }

    public StudentDTO(String studentId, String firstName, String lastName, String email, String username, List<Course> courses) {
        this.id = id;
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.courses = courses;
    }

    public StudentDTO(String studentId, String firstName, String lastName, String email, String username, List<Course> courses, String startTime, String endTime, boolean graduated) {
        this.id = id;
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.courses = courses;
        this.startTime = startTime;
        this.endTime = endTime;
        this.graduated = graduated;
    }

    public StudentDTO(long id, String studentId, String firstName, String lastName, String email, String username, List<Course> courses) {
        this.id = id;
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.courses = courses;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public boolean isGraduated() {
        return graduated;
    }

    public void setGraduated(boolean graduated) {
        this.graduated = graduated;
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
