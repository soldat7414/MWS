package com.Soldat.MWS.RESTcontrollers.entity_controllers;

import com.Soldat.MWS.entity.FileEntity;
import com.Soldat.MWS.entity.models.document_models.Document;
import com.Soldat.MWS.entity.models.file_models.File;
import com.Soldat.MWS.entity.supporting_classes.User;
import com.Soldat.MWS.exceptions.AlreadyExistException;
import com.Soldat.MWS.exceptions.NotFoundException;
import com.Soldat.MWS.services.FileService;
import com.Soldat.MWS.services.ServiceE;
import com.Soldat.MWS.services.utils.Functions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    FileService service;

    @GetMapping
    public ResponseEntity getAll() {
        try {
            return ResponseEntity.ok(File.toModelList(service.getAll()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.toString());
        }
    }

    @GetMapping("/by/{entityId}")
    public ResponseEntity<?> getBy(@PathVariable long entityId,
                                   @RequestParam String function){
        try{
            return ResponseEntity.ok(File.toModelList(
                    service.getBy(entityId, Functions.valueOf(function.toUpperCase()))));
        }catch (NotFoundException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
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
    public ResponseEntity add(@RequestBody FileEntity file,
                              @AuthenticationPrincipal User user) {
        try {
            file.setAuthor(user);
            return ResponseEntity.ok(service.add(file));
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
    public ResponseEntity binding(@RequestParam long entityId,
                                  @RequestParam long linkId,
                                  @RequestParam String function) {
        try {
            return ResponseEntity.ok(service.binding(entityId, linkId, Functions.valueOf(function)).toModel());
        } catch (NotFoundException | IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity edit(@RequestBody FileEntity entity,
                               @PathVariable long id){
        try {
            return ResponseEntity.ok(service.edit(id, entity).toModel());
        } catch (NotFoundException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
