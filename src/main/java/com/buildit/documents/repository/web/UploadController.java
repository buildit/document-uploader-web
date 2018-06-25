package com.buildit.documents.repository.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {

    @Autowired
    private S3Wrapper s3Wrapper;

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        s3Wrapper.upload(new MultipartFile[] {file});
        return "redirect:/uploadStatus";
    }


}
