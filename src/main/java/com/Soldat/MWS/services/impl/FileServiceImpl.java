package com.Soldat.MWS.services.impl;

import com.Soldat.MWS.entity.*;
import com.Soldat.MWS.exceptions.FileAlreadyExistException;
import com.Soldat.MWS.exceptions.FileNotFoundException;
import com.Soldat.MWS.exceptions.NotFoundException;
import com.Soldat.MWS.repository.FileRepo;
import com.Soldat.MWS.services.FileService;
import com.Soldat.MWS.services.ServiceE;
import com.Soldat.MWS.services.utils.Functions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Scope("singleton")
public class FileServiceImpl implements FileService {

    @Autowired
    private FileRepo repo;
    @Autowired
    private ServiceE<DocumentEntity> docService;

    public FileServiceImpl() {
    }

    @Override
    public long add(FileEntity file) throws FileAlreadyExistException {
        Optional<FileEntity> entity = repo.findByTitle(file.getTitle());
        if (entity.isPresent()) throw new FileAlreadyExistException(
                "Такий файл вже записано до бази даних.", entity.get().getId());
        file.setLastChange(new Date());
        System.out.println(file);
        return repo.save(file).getId();
    }

    @Override
    public FileEntity getById(long id) throws FileNotFoundException {
        Optional<FileEntity> file = repo.findById(id);
        if (file.isPresent()) return file.get();
        else throw new FileNotFoundException("Файл не знайдено.");
    }

    public FileEntity getByTitle(String title) throws FileNotFoundException {
        Optional<FileEntity> file = repo.findByTitle(title);
        if (file.isPresent()) return file.get();
        else throw new FileNotFoundException("Файл не знайдено.");
    }

    @Override
    public List<FileEntity> getAll() {
        Iterable<FileEntity> files = repo.findAll();
        List<FileEntity> all = new ArrayList<>();
        files.forEach(all::add);
        return all;
    }

    public List<FileEntity> getBy(long entityId, Functions function) throws NotFoundException {
        Iterable<FileEntity> files = null;
        switch (function){
            case DOCUMENT:{
                DocumentEntity doc = docService.getById(entityId);
                files = repo.findByDocument(doc);
                break;
            }

        }
        List<FileEntity> all = new ArrayList<>();
        if(files!=null) files.forEach(all::add);
        return all;
    }

    @Override
    public long delete(long id) throws FileNotFoundException {
        repo.delete(this.getById(id));
        return id;
    }
    public long delete(String title) throws FileNotFoundException {
        FileEntity entity = this.getByTitle(title);
        repo.delete(entity);
        return entity.getId();
    }

    @Override
    public FileEntity edit(long id, FileEntity entity) throws FileNotFoundException {
        FileEntity file = this.getById(id);
        if(entity.getTitle()!=null)file.setTitle(entity.getTitle());
        if(entity.getFileType()!=null)file.setFileType(entity.getFileType());
        if(entity.getItem()!=null)file.setItem(entity.getItem());
        file.setLastChange(new Date());
        return repo.save(file);
    }

    @Override
    public FileEntity binding(long entityId, long linkId, Functions function) throws NotFoundException {
        FileEntity file = this.getById(entityId);
        switch (function){
            case ADD_DOCUMENT:{
                DocumentEntity doc = docService.getById(linkId);
                file.setDocument(doc);
                break;
            }
        }
        return repo.save(file);
    }

}