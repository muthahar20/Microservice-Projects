package com.mtr.springboot_upload_cvsfile.controller;

import com.mtr.springboot_upload_cvsfile.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
@Slf4j
public class FileController {

    private final FileService fileService;

    @GetMapping
    public String getMsg(){
        return "<h1 style = font-color=red><center><br><br><br>Spring boot Application with File Upload Features </center></h1>";
    }

    @PostMapping(path = "/file-upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadFile(@RequestParam(name="file") MultipartFile file){
        log.info("FileController::uploadFile.Received File: {}", file.getOriginalFilename());
        return fileService.uploadFile(file);
    }

}
