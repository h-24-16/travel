package com.travel.api.endpoint.rest.controller;

import com.travel.api.model.FileDB;
import com.travel.api.service.impl.FileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@AllArgsConstructor
@RestController
@RequestMapping("/files")
@Tag(name = "file")
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

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        try {
            byte[] fileData = fileService.loadFileAsResource(id);
            FileDB image = fileService.getFile(id);

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(image.getType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getName() + "\"")
                    .body(fileData);
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}