package com.Soldat.MWS.controllers.facade_controllers;

import com.Soldat.MWS.entity.FileEntity;
import com.Soldat.MWS.entity.supporting_classes.UploadModel;
import com.Soldat.MWS.exceptions.AlreadyExistException;
import com.Soldat.MWS.services.controller_services.UpDownLoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

import static java.util.UUID.randomUUID;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    UpDownLoadService service;


    @PostMapping (consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?>uploadFile(@ModelAttribute UploadModel entity) throws IOException {
        try{
            System.out.println(entity);
            return ResponseEntity.ok(service.uploadFile(entity.getFile(), entity.getTitle(), entity.getFileType()));
        } catch (IOException | AlreadyExistException ex){
            return ResponseEntity.badRequest().body("File didn't upload, because: " + ex.getMessage() + ex.toString());
        }

    }
}
//    @RequestParam("file") MultipartFile file,
//    @RequestParam String title,
//    @RequestParam String fileType