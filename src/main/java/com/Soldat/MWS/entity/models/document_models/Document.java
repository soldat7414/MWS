package com.Soldat.MWS.entity.models.document_models;

import com.Soldat.MWS.entity.DocumentEntity;
import com.Soldat.MWS.entity.models.file_models.File;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Document {
    private long id;
    private String title;
    private String whatDocument;
    private List<File> items;
    private Date createDate;
    private Date lastChangeDate;
    private String description;

    //toModel
    public static Document toModel (DocumentEntity entity){
        Document doc = new Document();
        if(entity.getId()!=0)doc.setId(entity.getId());
        if(entity.getTitle()!=null)doc.setTitle(entity.getTitle());
        if(entity.getWhatDocument()!=null)doc.setWhatDocument(entity.getWhatDocument());
        doc.setCreateDate(entity.getCreateDate());
        doc.setLastChangeDate(entity.getLastChangeDate());
        if(entity.getDescription()!=null)doc.setDescription(entity.getDescription());
        if(entity.getItems()!=null)doc.setItems(File.toModelList(entity.getItems()));
        return doc;
    }

    public static List<Document> toModelList (List<DocumentEntity> docs){
        return docs.stream().map(Document::toModel).collect(Collectors.toList());
    }

    //constructor
    public Document() {
    }

    //getters and setters
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

    public String getWhatDocument() {
        return whatDocument;
    }

    public void setWhatDocument(String whatDocument) {
        this.whatDocument = whatDocument;
    }

    public List<File> getItems() {
        return items;
    }

    public void setItems(List<File> items) {
        this.items = items;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastChangeDate() {
        return lastChangeDate;
    }

    public void setLastChangeDate(Date lastChangeDate) {
        this.lastChangeDate = lastChangeDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
