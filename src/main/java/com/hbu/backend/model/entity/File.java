package com.hbu.backend.model.entity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;

@Entity
@Data
@ToString(includeFieldNames = true)
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private Integer fileId;

    @Nationalized
    private String fileName;
    @Nationalized
    private String contentType;
    @Nationalized
    private String fileSize;
    private Integer userId;
    private byte[] fileData;

//    public File() {
//    }
//
//    public File( String fileName, String contentType, String fileSize, Integer userId,  byte[] fileData) {
//        this.fileName = fileName;
//        this.contentType = contentType;
//        this.fileSize = fileSize;
//        this.userId = userId;
//        this.fileData = fileData;
//    }
//
//    public File(Integer fileId, String fileName, String contentType, String fileSize, Integer userId, byte[] fileData) {
//        this.fileId = fileId;
//        this.fileName = fileName;
//        this.contentType = contentType;
//        this.fileSize = fileSize;
//        this.userId = userId;
//        this.fileData = fileData;
//    }

//    public Integer getFileId() {
//        return fileId;
//    }
//
//    public void setFileId(Integer fileId) {
//        this.fileId = fileId;
//    }
//
//    public String getFileName() {
//        return fileName;
//    }
//
//    public void setFileName(String fileName) {
//        this.fileName = fileName;
//    }
//
//    public String getContentType() {
//        return contentType;
//    }
//
//    public void setContentType(String contentType) {
//        this.contentType = contentType;
//    }
//
//    public String getFileSize() {
//        return fileSize;
//    }
//
//    public void setFileSize(String fileSize) {
//        this.fileSize = fileSize;
//    }
//
//    public Integer getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Integer userId) {
//        this.userId = userId;
//    }
//
//    public byte[] getFileData() {
//        return fileData;
//    }
//
//    public void setFileData(byte[] fileData) {
//        this.fileData = fileData;
//    }
}
