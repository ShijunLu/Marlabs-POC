package com.demo.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demo.controller.filestorage.FileStorage;
 

  
@RestController
public class UploadFileController {
   
  @Autowired 
  FileStorage fileStorage;
  
    /*
     * MultipartFile Upload
     */
    @PostMapping("/api/file/upload")
    public String uploadMultipartFile(@RequestParam("uploadfile") MultipartFile file) {
      try {
        fileStorage.store(file);
        return "File uploaded successfully! -> filename = " + file.getOriginalFilename();
    } catch (Exception e) {
      return "Error -> message = " + e.getMessage();
    }    
    }
}