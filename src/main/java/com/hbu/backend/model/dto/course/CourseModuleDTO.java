package com.hbu.backend.model.dto.course;

import java.util.List;
import java.util.Map;

public class CourseModuleDTO {
    private Long id;
    private Long courseId;
    private Long instructorId;
    private List<Long> chapterIds;
    Map<Long, Double> studentScore;
}
