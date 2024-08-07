package com.travel.api.service.impl;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class UploadDir {
    @Value("${file.upload.dir}")
    String uploadDir;
}