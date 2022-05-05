package com.Soldat.MWS.controllers;

import com.Soldat.MWS.entity.AddressEntity;
import com.Soldat.MWS.entity.models.address_models.Address;
import com.Soldat.MWS.exceptions.AlreadyExistException;
import com.Soldat.MWS.exceptions.NotFoundException;
import com.Soldat.MWS.services.ServiceE;
import com.Soldat.MWS.services.utils.Functions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    ServiceE<AddressEntity> service;

    @GetMapping
    public ResponseEntity getAll() {
        try {
            return ResponseEntity.ok(Address.toModelList(service.getAll()));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity add(@RequestBody AddressEntity address) {
        try {
            return ResponseEntity.ok(service.add(address).toModel());
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
    public ResponseEntity addPerson(@RequestParam long idAddr,
                                    @RequestParam long idPers,
                                    @RequestParam String function) {
        try {
            return ResponseEntity.ok(service.binding(idAddr, idPers, Functions.valueOf(function)).toModel());
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
