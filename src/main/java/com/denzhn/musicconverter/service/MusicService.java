package com.denzhn.musicconverter.service;

import com.denzhn.musicconverter.dto.ConvertResponseDto;
import com.denzhn.musicconverter.dto.FileDto;

public interface MusicService {
    ConvertResponseDto convert(FileDto fileDto);
}
