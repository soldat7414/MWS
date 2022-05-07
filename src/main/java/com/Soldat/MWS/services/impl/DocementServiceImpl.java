package com.Soldat.MWS.services.impl;

import com.Soldat.MWS.entity.ObjectEntity;
import com.Soldat.MWS.entity.OrganizationEntity;
import com.Soldat.MWS.entity.DocumentEntity;
import com.Soldat.MWS.entity.PersonEntity;
import com.Soldat.MWS.exceptions.NotFoundException;
import com.Soldat.MWS.exceptions.DocumentAlreadyExistException;
import com.Soldat.MWS.exceptions.DocumentNotFoundException;
import com.Soldat.MWS.repository.DocumentRepo;
import com.Soldat.MWS.services.DocumentService;
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
public class DocementServiceImpl implements DocumentService {

    @Autowired
    DocumentRepo repo;
    @Autowired
    ServiceE<OrganizationEntity> orgService;
    @Autowired
    ServiceE<PersonEntity> personService;
    @Autowired
    ServiceE<ObjectEntity> objService;

    private long containSameInDB(DocumentEntity doc) {
        Optional<DocumentEntity> result = repo.findByTitleAndAndDescriptionAndAndDocType(doc.getTitle(),
                doc.getDescription(), doc.getDocType());
        return result.map(DocumentEntity::getId).orElse(-1L);
    }

    @Override
    public DocumentEntity add(DocumentEntity doc) throws DocumentAlreadyExistException {
        long id = containSameInDB(doc);
        if (id > 0) throw new DocumentAlreadyExistException("Такий документ вже записаний до бази даних.", id);
        doc.setLastChangeDate(new Date());
        return repo.save(doc);
    }

    @Override
    public DocumentEntity getById(long id) throws DocumentNotFoundException {
        Optional<DocumentEntity> doc = repo.findById(id);
        if (doc.isPresent()) return doc.get();
        else throw new DocumentNotFoundException("Документ не знайдено.");
    }

    @Override
    public List<DocumentEntity> getAll() {
        Iterable<DocumentEntity> docs = repo.findAll();
        List<DocumentEntity> all = new ArrayList<>();
        docs.forEach(all::add);
        return all;
    }

    @Override
    public long delete(long id) throws DocumentNotFoundException {
        repo.delete(this.getById(id));
        return id;
    }

    @Override
    public DocumentEntity edit(long id, DocumentEntity entity) throws DocumentNotFoundException {
        DocumentEntity doc = this.getById(id);
        if(entity.getTitle()!=null)doc.setTitle(entity.getTitle());
        if(entity.getDocType()!=null)doc.setDocType(entity.getDocType());
        if(entity.getDescription()!=null)doc.setDescription(entity.getDescription());
        doc.setLastChangeDate(new Date());
        return repo.save(doc);
    }

    @Override
    public DocumentEntity binding(long entityId, long linkId, Functions function) throws NotFoundException {
        DocumentEntity doc = this.getById(entityId);
        switch (function){
            case ADD_ORGANIZATION:{
                OrganizationEntity org = orgService.getById(linkId);
                doc.setOrganization(org);
                break;
            }
            case ADD_PERSON:{
                PersonEntity person = personService.getById(linkId);
                doc.setPerson(person);
                break;
            }
            case ADD_OBJECT:{
                ObjectEntity obj = objService.getById(linkId);
                doc.setObject(obj);
                break;
            }
        }
        doc.setLastChangeDate(new Date());
        return repo.save(doc);
    }
}
