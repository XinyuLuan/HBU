package com.hbu.backend.model.dto;

import lombok.Data;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Data
@ToString(includeFieldNames = true)
public class FileDTO {
    private String fileId;
    private MultipartFile updatedFile;

    public FileDTO() {
    }

    public FileDTO(String fileId, MultipartFile updatedFile) {
        this.fileId = fileId;
        this.updatedFile = updatedFile;
    }
}