package com.Soldat.MWS.controllers;

import com.Soldat.MWS.entity.PersonEntity;
import com.Soldat.MWS.entity.models.Person;
import com.Soldat.MWS.exceptions.AlreadyExistException;
import com.Soldat.MWS.exceptions.NotFoundException;
import com.Soldat.MWS.services.ServiceE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    ServiceE<PersonEntity> serviceE;

    @GetMapping
    public ResponseEntity getAll() {
        try {
            return ResponseEntity.ok(Person.toModelList(serviceE.getAll()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.toString());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable long id) {
        try {
            return ResponseEntity.ok(serviceE.getById(id).toModel());
        } catch (NotFoundException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity add(@RequestBody PersonEntity person) {
        try {
            return ResponseEntity.ok(serviceE.add(person).toModel());
        } catch (AlreadyExistException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage() + ex.getId());
        }
    }
}
