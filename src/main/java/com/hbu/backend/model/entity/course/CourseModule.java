package com.hbu.backend.model.entity.course;

import com.hbu.backend.model.entity.Instructor;
import com.hbu.backend.model.entity.Student;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class CourseModule {
    private Long id;
    private Course course;
    private Instructor instructor;
    private List<Chapter> chapters;
    private List<Section> sections;
    private List<Student> students;

}
