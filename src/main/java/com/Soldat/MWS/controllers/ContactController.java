package com.Soldat.MWS.controllers;

import com.Soldat.MWS.entity.ContactEntity;
import com.Soldat.MWS.entity.models.contact_models.Contact;
import com.Soldat.MWS.exceptions.AlreadyExistException;
import com.Soldat.MWS.exceptions.NotFoundException;
import com.Soldat.MWS.services.ServiceE;
import com.Soldat.MWS.services.utils.Functions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    ServiceE<ContactEntity> service;

    @GetMapping
    public ResponseEntity getAll() {
        try {
            return ResponseEntity.ok(Contact.toModelList(service.getAll()));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity add(@RequestBody ContactEntity contact) {
        try {
            return ResponseEntity.ok(service.add(contact).toModel());
        } catch (AlreadyExistException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage() + ex.getId());
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

    @PutMapping
    public ResponseEntity binding(@RequestParam long idContact,
                                  @RequestParam long idPers,
                                  @RequestParam Functions function) {
        try {
            return ResponseEntity.ok(service.binding(idContact, idPers, function).toModel());
        } catch (NotFoundException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
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
}
