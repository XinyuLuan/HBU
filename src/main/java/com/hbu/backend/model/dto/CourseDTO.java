package com.hbu.backend.model.dto;


import java.time.LocalDateTime;

// reference: https://lucid.app/lucidchart/a896735c-2466-4dcd-8d67-2851ce3d98b0/edit?beaconFlowId=6357EB40A9CF55A9&page=0_0#?folder_id=home&browser=icon
public class CourseDTO {
    private Long courseId;
    private String Title;
    private String classSubject;
    private Long InstructorId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getClassSubject() {
        return classSubject;
    }

    public void setClassSubject(String classSubject) {
        this.classSubject = classSubject;
    }

    public Long getInstructorId() {
        return InstructorId;
    }

    public void setInstructorId(Long instructorId) {
        InstructorId = instructorId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
