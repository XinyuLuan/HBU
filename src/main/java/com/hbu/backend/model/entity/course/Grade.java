package com.hbu.backend.model.entity.course;

import com.hbu.backend.model.entity.Student;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
//@Data
//@EqualsAndHashCode(exclude="courseModule")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courseModule_id")
    private CourseModule courseModule;

//    @ManyToOne(targetEntity = Student.class)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    private Double gradeValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CourseModule getCourseModule() {
        return courseModule;
    }

    public void setCourseModule(CourseModule courseModule) {
        this.courseModule = courseModule;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Double getGradeValue() {
        return gradeValue;
    }

    public void setGradeValue(Double gradeValue) {
        this.gradeValue = gradeValue;
    }
}
