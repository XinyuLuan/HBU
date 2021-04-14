package com.hbu.backend.model.entity.course;

import com.hbu.backend.model.entity.Instructor;

import java.util.List;

public class CourseModule {
    private Long id;
    private Course course;
    private Instructor instructor;
    private List<Chapter> chapters;

}
