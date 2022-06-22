package com.denzhn.musicconverter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ConvertResponseDto {
    private byte[] data;
    private String mimeType;
}
