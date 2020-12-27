package com.udacity.jwdnd.course1.cloudstorage.models;

public class ResponseFile {
    private String fileName;
    private String url;
    private String contentType;
    private long fileSize;
    private Integer fileId;

    public ResponseFile(String fileName, String url, String contentType, long fileSize, Integer fileId) {
        this.fileName = fileName;
        this.url = url;
        this.contentType = contentType;
        this.fileSize = fileSize;
        this.fileId = fileId;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }
}
