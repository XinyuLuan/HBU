package com.hbu.backend.model.dto.course;

import lombok.Data;
import java.util.List;

@Data
public class SectionDTO {
    private Long id;
    private String title;
    private SectionType sectionType;
    private List<Long> componentIds;
}
