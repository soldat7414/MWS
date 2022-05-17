package com.Soldat.MWS.RESTcontrollers.entity_controllers;

import com.Soldat.MWS.entity.DocumentEntity;
import com.Soldat.MWS.entity.FileEntity;
import com.Soldat.MWS.entity.models.document_models.Document;
import com.Soldat.MWS.entity.models.file_models.File;
import com.Soldat.MWS.exceptions.AlreadyExistException;
import com.Soldat.MWS.exceptions.NotFoundException;
import com.Soldat.MWS.services.ServiceE;
import com.Soldat.MWS.services.utils.Functions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/document")
public class DocumentController {

    @Autowired
    ServiceE<DocumentEntity> service;

    @GetMapping
    public ResponseEntity getAll() {
        try {
            return ResponseEntity.ok(Document.toModelList(service.getAll()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.toString());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable long id) {
        try {
            return ResponseEntity.ok(service.getById(id).toModel());
        } catch (NotFoundException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity add(@RequestBody DocumentEntity document) {
        try {
            return ResponseEntity.ok(service.add(document).toModel());
        } catch (AlreadyExistException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage() + ex.getId());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable long id) {
        try {
            return ResponseEntity.ok(service.delete(id));
        } catch (NotFoundException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity binding(@RequestParam long idPers,
                                  @RequestParam long idOrg,
                                  @RequestParam String function) {
        try {
            return ResponseEntity.ok(service.binding(idPers, idOrg, Functions.valueOf(function)).toModel());
        } catch (NotFoundException | IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity edit(@RequestBody DocumentEntity entity,
                               @PathVariable long id){
        try {
            return ResponseEntity.ok(service.edit(id, entity).toModel());
        } catch (NotFoundException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
