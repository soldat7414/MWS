package com.Soldat.MWS.services.controller_services;

import com.Soldat.MWS.entity.FileEntity;
import com.Soldat.MWS.exceptions.AlreadyExistException;
import com.Soldat.MWS.services.ServiceE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@Service
@Scope("singleton")
public class UpDownLoadService {

    @Autowired
    ServiceE<FileEntity> fileService;
    @Value("${upload.path}")
    String uploadPath;

    public String uploadFile(MultipartFile uploadFile, String title,
                             String fileType) throws IOException, AlreadyExistException {

        if (uploadFile != null) {
            System.out.println(uploadPath);
            File uploadDir = new File(uploadPath, fileType);
            if (!uploadDir.exists()) uploadDir.mkdirs();
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + uploadFile.getOriginalFilename();

            System.out.println("mark1");
            //String dest = uploadDir.getPath() + "\\" + resultFileName;
            System.out.println("mark2");

            uploadFile.transferTo(new File(uploadDir, resultFileName));

            System.out.println("mark3");


            fileService.add(new FileEntity(title, new Date(), fileType, resultFileName));

            //uploadFile.transferTo(new File(dest));
            return "File uploaded";
        }
        return "There none file";

    }
}
