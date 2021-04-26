package com.hbu.backend.model.entity.course;

import com.hbu.backend.model.entity.Student;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(exclude="courseModule")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = CourseModule.class)
    private CourseModule courseModule;

    @OneToOne(targetEntity = Student.class)
    private Student student;

    private Double gradeValue;
}
