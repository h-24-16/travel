package com.travel.api.controller;

import com.travel.api.model.FileDB;
import com.travel.api.service.impl.FileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@AllArgsConstructor
@RestController
@RequestMapping("/files")
public class FileController {

    private FileService fileService;

    @PostMapping
    public ResponseEntity<FileDB> uploadFile(@RequestBody MultipartFile file) {
        try {
            FileDB fileDB = fileService.saveFile(file);
            return new ResponseEntity<>(fileDB, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

}