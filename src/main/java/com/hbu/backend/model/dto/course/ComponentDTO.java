package com.hbu.backend.model.dto.course;

import com.hbu.backend.model.dto.FileDTO;
import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Data
@ToString(includeFieldNames = true)
public class ComponentDTO {

//    Long getId();
//    String getTitle();
//    String getDescription();
//    String getComponentType();
//
//    void setId(Long id);
//    void setDescription(String description);
//    void setTitle(String title);
//    void setComponentType(String componentType);
    private Long id;
    private String title;
    private String description;
    private ComponentType componentType;

    /**
     * for FileComponent
     */
    private List<FileDTO> fileId;

    /**
     * for TextComponent
     */
    private String inputText;

    /**
     * for MultipleChoice
     */
    private Map<String, Boolean> choices;
}
