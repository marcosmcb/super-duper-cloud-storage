package com.udacity.jwdnd.course1.cloudstorage.models;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.IOException;

public class File {

    private Integer fileId;

    @NotNull
    private Integer userId;

    @NotNull
    private String fileName;

    @NotNull
    private String contentType;

    @NotNull
    @Min(value = 1, message = "file must not be empty")
    private Long fileSize;

    @NotNull
    private byte[] fileData;

    public File(Integer fileId, String fileName, String contentType, Long fileSize, Integer userId, byte[] fileData) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.contentType = contentType;
        this.fileSize = fileSize;
        this.userId = userId;
        this.fileData = fileData;
    }

    public File(MultipartFile fileForm, Integer userId) throws IOException {
        this.fileData = fileForm.getBytes();
        this.fileName = fileForm.getOriginalFilename();
        this.fileSize = fileForm.getSize();
        this.contentType = fileForm.getContentType();
        this.userId = userId;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }
}
