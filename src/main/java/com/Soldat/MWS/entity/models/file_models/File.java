package com.Soldat.MWS.entity.models.file_models;

import com.Soldat.MWS.entity.DocumentEntity;
import com.Soldat.MWS.entity.FileEntity;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class File {
    private long id;
    private String title;
    private Date addDate;
    private Date lastChange;
    private String fileType;
    private String item;
    private DocumentEntity document;

    //toModel
    public static File toModel (FileEntity entity){
        File file = new File();
        if(entity.getId()!=0)file.setId(entity.getId());
        file.setAddDate(entity.getAddDate());
        file.setLastChange(entity.getLastChange());
        if(entity.getFileType()!=null)file.setFileType(entity.getFileType());
        if(entity.getTitle()!=null)file.setTitle(entity.getTitle());
        if(entity.getFileType()!=null)file.setFileType(entity.getFileType());
        if(entity.getItem()!=null)file.setItem(entity.getItem());
            return file;
    }

    public static List<File> toModelList (List<FileEntity> files){
        return files.stream().map(File::toModel).collect(Collectors.toList());
    }

    public File() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public Date getLastChange() {
        return lastChange;
    }

    public void setLastChange(Date lastChange) {
        this.lastChange = lastChange;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public DocumentEntity getDocument() {
        return document;
    }

    public void setDocument(DocumentEntity document) {
        this.document = document;
    }

}
