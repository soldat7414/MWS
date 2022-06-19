package com.Soldat.MWS.RESTcontrollers.facade_controllers;

import com.Soldat.MWS.entity.supporting_classes.UploadModel;
import com.Soldat.MWS.exceptions.AlreadyExistException;
import com.Soldat.MWS.exceptions.NotFoundException;
import com.Soldat.MWS.services.controller_services.UpDownLoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/files")
public class UploadController {

    @Autowired
    UpDownLoadService service;


    @PostMapping (consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?>uploadFile(@ModelAttribute UploadModel entity){
        try{
            System.out.println(entity);
            return ResponseEntity.ok(service.uploadFile(entity.getFile(), entity.getTitle(), entity.getFileType()));
        } catch (IOException | AlreadyExistException ex){
            return ResponseEntity.badRequest().body("File didn't upload, because: " + ex.getMessage() + ex.toString());
        }
    }

    @GetMapping("/{fileType}/{title}")
    public ResponseEntity<?> downloadFile(@PathVariable String title,
                                          @PathVariable String fileType){
        try{
            return ResponseEntity.ok(service.downloadFile(title, fileType));
        }catch (IOException | NotFoundException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/{title}")
    public ResponseEntity<?> deleteFile(@PathVariable String title,
                                          @RequestParam String fileType){
        try{
            return ResponseEntity.ok(service.deleteFile(title, fileType));
        }catch (NotFoundException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
