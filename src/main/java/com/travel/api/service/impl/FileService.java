package com.travel.api.service.impl;

import com.travel.api.model.FileDB;
import com.travel.api.repository.FileRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    @Value("${file.upload.dir}")
    private String uploadDir;

    public FileDB saveFile(MultipartFile file) throws IOException {
        // Generate a unique file name
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

        // Save the file to the filesystem
        Path copyLocation = Paths.get(uploadDir + File.separator + fileName);
        Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);

        // Save the path and metadata to the database
        FileDB toSave = new FileDB();
        toSave.setName(file.getOriginalFilename());
        toSave.setType(file.getContentType());
        toSave.setPath(copyLocation.toString());
        return fileRepository.save(toSave);
    }

    public FileDB getFile(Long id) {
        return fileRepository.findById(id).orElseThrow(() -> new RuntimeException("File not found"));
    }

    public byte[] loadFileAsResource(Long id) throws IOException {
        FileDB image = getFile(id);
        Path path = Paths.get(image.getPath());
        return Files.readAllBytes(path);
    }

}