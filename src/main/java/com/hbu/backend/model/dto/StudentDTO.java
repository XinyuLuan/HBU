package com.hbu.backend.model.dto;

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
    private RoleType roleType;

    private List<Long> courseModuleIds;
    private String startTime;
    private String endTime;
    private boolean graduated;

    public StudentDTO() {
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

    public List<Long> getCourseModuleIds() {
        return courseModuleIds;
    }

    public void setCourseModuleIds(List<Long> courseModuleIds) {
        this.courseModuleIds = courseModuleIds;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
