package com.Soldat.MWS.services.controller_services;

import com.Soldat.MWS.entity.FileEntity;
import com.Soldat.MWS.exceptions.AlreadyExistException;
import com.Soldat.MWS.exceptions.FileNotFoundException;
import com.Soldat.MWS.services.ServiceE;
import com.Soldat.MWS.services.impl.FileServiceImpl;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Scope("singleton")
public class UpDownLoadService {

    @Autowired
    FileServiceImpl fileService;
    @Value("${upload.path}")
    String uploadPath;

    public long uploadFile(MultipartFile uploadFile, String title,
                             String fileType) throws IOException, AlreadyExistException {

        if (uploadFile != null) {
            File uploadDir = new File(uploadPath, fileType);
            if (!uploadDir.exists()) uploadDir.mkdirs();
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + uploadFile.getOriginalFilename();
            File item = new File(uploadDir, resultFileName);
            item.setReadable(true);
            uploadFile.transferTo(item);
            FileEntity file = new FileEntity();
            file.setTitle(title);
            file.setFileType(fileType);
            file.setAddDate(new Date());
            file.setItem(item.getAbsolutePath());
            long idFile = fileService.add(file);
            return idFile;
        }
        return -1;
    }

    public Resource downloadFile(String title, String fileType) throws FileNotFoundException, MalformedURLException {
        File downloadDir = new File(uploadPath, fileType);
        if(downloadDir.exists()){
            File downloadF = new File(downloadDir, title);
            if(downloadF.exists()) return new FileUrlResource(String.valueOf(downloadF));
            throw new FileNotFoundException("file didn't find");
        }
        throw new FileNotFoundException("directory didn't find");
    }


    public Stream<Resource> downloadAllFiles(){
        List<FileEntity> files = fileService.getAll();
       return files.stream().map(file -> {
           try {
               return this.downloadFile(file.getTitle(), file.getFileType());
           } catch (FileNotFoundException e) {
               e.printStackTrace();
           } catch (MalformedURLException e) {
               e.printStackTrace();
           }
           return null;
       }).collect(Collectors.toList()).stream();
    }

    public String deleteFile(String title, String fileType) throws FileNotFoundException{
        File downloadDir = new File(uploadPath, fileType);
        if(downloadDir.exists()){
            File downloadF = new File(downloadDir, title);
            if(downloadF.exists()) {
                downloadF.delete();
                fileService.delete(title);
                return "???????? "+title.substring(title.indexOf('.'))+" ????????????????.";
            }
            throw new FileNotFoundException("file didn't find");
        }
        throw new FileNotFoundException("directory didn't find");
    }




}
