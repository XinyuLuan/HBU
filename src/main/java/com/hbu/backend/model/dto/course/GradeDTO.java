package com.hbu.backend.model.dto.course;

import lombok.Data;

@Data
public class GradeDTO {
    private Long id;
    private Long studentId;
    private Long courseModuleId;
    private Double gradeValue;
}
