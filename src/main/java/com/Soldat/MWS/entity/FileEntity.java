package com.Soldat.MWS.entity;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "file")
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private Date addDate;
    private Date lastChange;
    private String fileType;
    private String item;

    @ManyToOne
    @PrimaryKeyJoinColumn
    private DocumentEntity document;

    //toModel
    public com.Soldat.MWS.entity.models.file_models.File toModel(){
        return com.Soldat.MWS.entity.models.file_models.File.toModel(this);
    }

    public FileEntity() {
    }

    public FileEntity(String title, Date addDate, String fileType, String item) {
        this.title = title;
        this.addDate = addDate;
        this.fileType = fileType;
        this.item = item;
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

    public Date getLastChange() {
        return lastChange;
    }

    public void setLastChange(Date lastChange) {
        this.lastChange = lastChange;
    }

    public DocumentEntity getDocument() {
        return document;
    }

    public void setDocument(DocumentEntity document) {
        this.document = document;
    }

    @Override
    public String toString() {
        return "FileEntity{" +
                "\nid=" + id +
                "\n, title='" + title + '\'' +
                "\n, addDate=" + addDate +
                "\n, lastChange=" + lastChange +
                "\n, fileType='" + fileType + '\'' +
                "\n, item='" + item + '\'' +
                "\n, document=" + document +
                '}';
    }
}
