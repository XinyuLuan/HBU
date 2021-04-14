package com.hbu.backend.model.dto.course;

import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

// reference: https://lucid.app/lucidchart/a896735c-2466-4dcd-8d67-2851ce3d98b0/edit?beaconFlowId=6357EB40A9CF55A9&page=0_0#?folder_id=home&browser=icon
@ToString(includeFieldNames = true)
public class CourseDTO {

    private Long courseId; // 1
    private String Title;
    private String classSubject;
//    private Long InstructorId;
    private String courseNumber;
    private String description;

////    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
//    private LocalDateTime startTime;
////    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
//
//    private LocalDateTime endTime;

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

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

//    public Long getInstructorId() {
//        return InstructorId;
//    }
//
//    public void setInstructorId(Long instructorId) {
//        InstructorId = instructorId;
//    }

//    public LocalDateTime getStartTime() {
//        return startTime;
//    }
//
//    public void setStartTime(LocalDateTime startTime) {
//        this.startTime = startTime;
//    }
//
//    public LocalDateTime getEndTime() {
//        return endTime;
//    }
//
//    public void setEndTime(LocalDateTime endTime) {
//        this.endTime = endTime;
//    }
}
