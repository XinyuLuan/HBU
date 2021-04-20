package com.hbu.backend.model.dto.course;

import lombok.Data;

@Data
public class GradeDTO {
    private Long id;
    private String studentId;
    private Double gradeValue;
}
