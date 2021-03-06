package com.Soldat.MWS.RESTcontrollers.entity_controllers;

import com.Soldat.MWS.entity.ContactEntity;
import com.Soldat.MWS.entity.models.contact_models.Contact;
import com.Soldat.MWS.entity.supporting_classes.User;
import com.Soldat.MWS.exceptions.AlreadyExistException;
import com.Soldat.MWS.exceptions.NotFoundException;
import com.Soldat.MWS.services.ContactService;
import com.Soldat.MWS.services.ServiceE;
import com.Soldat.MWS.services.impl.ContactServiceImpl;
import com.Soldat.MWS.services.utils.Functions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    ContactServiceImpl service;

    @GetMapping
    public ResponseEntity getAll() {
        try {
            return ResponseEntity.ok(Contact.toModelList(service.getAll()));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
    @GetMapping("/org/{id}")
    public ResponseEntity getByOrg(@PathVariable long id) {
        try {
            return ResponseEntity.ok(Contact.toModelList(service.getByOrganization(id)));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/pers/{id}")
    public ResponseEntity getByPers(@PathVariable long id) {
        try {
            return ResponseEntity.ok(Contact.toModelList(service.getByPerson(id)));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity add(@RequestBody ContactEntity contact,
                              @AuthenticationPrincipal User user) {
        try {
            contact.setAuthor(user);
            return ResponseEntity.ok(service.add(contact));
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
    public ResponseEntity binding(@RequestParam long entityId,
                                  @RequestParam long linkId,
                                  @RequestParam String function) {
        try {
            return ResponseEntity.ok(service.binding(entityId, linkId, Functions.valueOf(function.toUpperCase())).toModel());
        } catch (NotFoundException | IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity edit(@RequestBody ContactEntity entity,
                               @PathVariable long id){
        try {
            return ResponseEntity.ok(service.edit(id, entity).toModel());
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
