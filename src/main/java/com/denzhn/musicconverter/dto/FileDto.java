package com.denzhn.musicconverter.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class FileDto {
    private MultipartFile data;
    private String targetType;
}
