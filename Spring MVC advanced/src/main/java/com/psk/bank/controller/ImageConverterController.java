package com.psk.bank.controller;

import java.io.IOException;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
 
@Controller
@RequestMapping("/image")
public class ImageConverterController {
    
    public static class Base64Image {
        private String base64;
        public Base64Image(String base64) {
            this.base64 = base64;
        }
        public String getContent() {
            return base64;
        }
    }
    
    public Base64Image lastImg;
    
    @PostMapping(value = "/encode",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public @ResponseBody String encodeImage(@RequestParam("file") MultipartFile file) throws IOException {
        lastImg = new Base64Image(Base64.encodeBase64String(file.getBytes()));
        return "encoded";
    }

    @GetMapping(value = "/decode", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> decodeImage() throws Exception {
        if(lastImg == null) {
            throw new Exception("No img");
        }
        return ResponseEntity.ok(Base64.decodeBase64(lastImg.getContent()));
    }
    
    @GetMapping(value = "/decode2", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody Base64Image decodeImage2() {
        return lastImg;
    }
}
