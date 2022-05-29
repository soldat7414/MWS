package com.Soldat.MWS.RESTcontrollers.entity_controllers;

import com.Soldat.MWS.entity.AddressEntity;
import com.Soldat.MWS.entity.models.address_models.Address;
import com.Soldat.MWS.entity.supporting_classes.User;
import com.Soldat.MWS.exceptions.AlreadyExistException;
import com.Soldat.MWS.exceptions.NotFoundException;
import com.Soldat.MWS.services.ServiceE;
import com.Soldat.MWS.services.utils.Functions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity add(@RequestBody AddressEntity address,
                              @AuthenticationPrincipal User user) {
        try {
            address.setAuthor(user);
            return ResponseEntity.ok(service.add(address));
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
    public ResponseEntity binding(@RequestParam long addrId,
                                  @RequestParam long linkId,
                                  @RequestParam String function) {
        try {
            return ResponseEntity.ok(service.binding(addrId, linkId, Functions.valueOf(function)).toModel());
        } catch (NotFoundException | IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity edit(@RequestBody AddressEntity entity,
                               @PathVariable long id) {
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
