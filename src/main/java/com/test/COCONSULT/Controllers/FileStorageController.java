package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.Interfaces.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class FileStorageController {
    @Autowired
    private FileStorageService fileStorageService;



    @Value("${file.upload-dir}")
    private String uploadDir;

   /* @GetMapping("/images/{imageName:.+}")
    public ResponseEntity<byte[]> getImage(@PathVariable String imageName) throws IOException {
        // Extract the actual filename from the request path
       // String actualFileName = imageName.substring(imageName.indexOf("_") + 1);

        // Construct the full path to the image file
        Path imagePath = Paths.get(uploadDir, imageName);

        // Read the image bytes from the file
        byte[] imageBytes = Files.readAllBytes(imagePath);

        // Determine the MIME type of the image based on its file extension
        String contentType = Files.probeContentType(imagePath);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(contentType));

        // Return the image bytes along with appropriate headers
        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }
    //@PostMapping("uploadimage")
    @PostMapping(value = "/uploadimage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

    public ResponseEntity<?> uploadImage(@RequestPart("image") MultipartFile file) {


        String fileName = fileStorageService.storeFile(file);
        if(!fileName.isEmpty()){
            return ResponseEntity.ok().body(fileName);}

        else
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("image not uploaded");
        }
    }*/


       // @PostMapping("/uploadpdf")
       @PostMapping(value = "/uploadpdf", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

       public String uploadFile(@RequestParam("file") MultipartFile file) {
            if (file.isEmpty()) {
                return "Please select a file to upload";
            }

            try {

                String fileName = fileStorageService.storeFile(file);
                return "File uploaded successfully: " + fileName;
            } catch (Exception e) {
                return "Failed to upload file: " + e.getMessage();
            }
        }
    }

