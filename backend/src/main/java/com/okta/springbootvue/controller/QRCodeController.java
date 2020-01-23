package com.okta.springbootvue.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;



import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class QRCodeController {
    
    @GetMapping(value = "/getimage",produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImageWithMediaType() throws IOException {
        
        InputStream in = getClass()
          .getResourceAsStream("BsC.PNG");
        if( in == null){
          throw new FileNotFoundException("readFilesInBytes: File " + "file"
          + " does not exist");
        }
        return IOUtils.toByteArray(in);
    }

}