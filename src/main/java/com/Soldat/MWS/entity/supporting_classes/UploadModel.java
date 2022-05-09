package com.Soldat.MWS.entity.supporting_classes;

import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.AccessType;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;


public class UploadModel implements Serializable {
    private MultipartFile file;
    private String title;
    private String fileType;

    //constructor, accessors


    public UploadModel() {
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @Override
    public String toString() {
        return "UploadModel{" +
                "file=" + file +
                ", title='" + title + '\'' +
                ", fileType='" + fileType + '\'' +
                '}';
    }
}
