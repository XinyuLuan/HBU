package com.hbu.backend.model.dto.course;

import com.hbu.backend.model.entity.course.Section;
import lombok.Data;

import java.util.List;

@Data
public class ChapterDTO {

    private Long id;
    private List<Long> sectionIds;

}
