package com.hbu.backend.model.dto.course;

import com.hbu.backend.model.entity.Student;
import com.hbu.backend.model.entity.course.Chapter;
import com.hbu.backend.model.entity.course.Grade;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
public class CourseModuleDTO {
    private Long id;
    private Long courseId;
    private Long instructorId;
    private List<Long> chapterIds;
    private Set<Long> studentIds;
    private Set<Long> gradeIds;
}
