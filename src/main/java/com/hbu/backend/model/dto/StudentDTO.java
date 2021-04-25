package com.hbu.backend.model.dto;

import com.hbu.backend.model.entity.course.Course;
import com.hbu.backend.model.utility.RoleType;

import java.util.List;

public class StudentDTO {
    // what about gpa info?
    private long id;
    private String studentUniversityId;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private List<Course> courses;
    private RoleType roleType;
    private String startTime;
    private String endTime;
    private boolean graduated;

    public StudentDTO(long id, String studentUniversityId, String firstName, String lastName, String email, String username, List<Course> courses, RoleType roleType) {
        this.id = id;
        this.studentUniversityId = studentUniversityId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.courses = courses;
        this.roleType = roleType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.graduated = graduated;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStudentUniversityId() {
        return studentUniversityId;
    }

    public void setStudentUniversityId(String studentUniversityId) {
        this.studentUniversityId = studentUniversityId;
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
