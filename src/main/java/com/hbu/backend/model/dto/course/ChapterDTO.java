package com.hbu.backend.model.dto.course;

import lombok.Data;

import java.util.List;

@Data
public class ChapterDTO {

    private Long id;
    private List<Long> sectionIds;
    private Long courseModuleId;
}
