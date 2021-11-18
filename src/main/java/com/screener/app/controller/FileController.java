package com.screener.app.controller;
import org.apache.tika.io.IOUtils;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.attribute.PosixFilePermission;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.screener.app.payload.*;
import com.screener.app.property.*;
import com.screener.app.services.FileStorageService;
import com.screener.app.services.inotificationService;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.attribute.PosixFilePermission;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.screener.app.controller.FileController;
import com.screener.app.entity.notes;
import com.screener.app.entity.taluka;

import org.slf4j.Logger;
import org.apache.tika.io.IOUtils;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
@RestController

@CrossOrigin(origins = {"*"},allowedHeaders="*")
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileStorageService fileStorageService;
    private inotificationService ns;

    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile[] file1,@RequestParam("notes_uuid") String notes_uuid,@RequestParam("checkImgSrc") int checkImgSrc) {
    	
    	
        String fileName = fileStorageService.storeFile(file1,notes_uuid,checkImgSrc);
        
        System.out.println("fileNameNEW--"+file1);
        MultipartFile file= file1[0];
		   
        System.out.println("fileNameNEW--"+fileName);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();
        
        System.out.println("fileDownloadUri--"+fileDownloadUri);

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @PostMapping("/uploadMultipleFiles")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files,@RequestParam("notes_uuid") String notes_uuid,@RequestParam("checkImgSrc") int checkImgSrc) {
    	
    	
    	if(checkImgSrc == 1) {
    		
    		System.out.println("checkImgSrc---froMNotification---   "+checkImgSrc);
    		uploadFile(files,notes_uuid,checkImgSrc);
    		
    	}else if(checkImgSrc == 2){
    		
    		System.out.println("checkImgSrc---froMpatient---   "+checkImgSrc);
    		uploadFile(files,notes_uuid,checkImgSrc);
    	}else if(checkImgSrc == 3) {
    		
    		System.out.println("checkImgSrc---froMpatient---   "+checkImgSrc);
    		uploadFile(files,notes_uuid,checkImgSrc);
    	}
    	
    	return null;
    	
//        return Arrays.asList(files)
//                .stream()
//                .map(file -> uploadFile(file,checkImgSrc))
//                .collect(Collectors.toList());
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return null;
//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(contentType))
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
//                .body(resource);
    }
 
  		
    
   
	

}