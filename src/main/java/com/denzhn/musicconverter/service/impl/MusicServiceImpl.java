package com.denzhn.musicconverter.service.impl;

import com.denzhn.musicconverter.dto.ConvertResponseDto;
import com.denzhn.musicconverter.dto.FileDto;
import com.denzhn.musicconverter.enums.SoundType;
import com.denzhn.musicconverter.exception.ConversionException;
import com.denzhn.musicconverter.helper.MimeTypeHelper;
import com.denzhn.musicconverter.helper.ValidationHelper;
import com.denzhn.musicconverter.service.MusicService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Service;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@Service
public class MusicServiceImpl implements MusicService {
    @Override
    public ConvertResponseDto convert(FileDto fileDto) {
        ValidationHelper.validateByteData(fileDto.getData());
        ValidationHelper.validateString(fileDto.getTargetType());
        try (BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(fileDto.getData().getBytes()));
        ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bis);
            SoundType format = SoundType.getFormat(fileDto.getTargetType());
            switch (Objects.requireNonNull(format)) {
                case WAV -> AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, bos);
                case AIFF -> AudioSystem.write(audioInputStream, AudioFileFormat.Type.AIFF, bos);
                case AU -> AudioSystem.write(audioInputStream, AudioFileFormat.Type.AU, bos);
                default -> throw new ConversionException("Requested Audio Type Not Supported.");
            }
            return new ConvertResponseDto(bos.toByteArray(), MimeTypeHelper.getMimeType(format));
        } catch (UnsupportedAudioFileException | IOException e) {
            log.error("Conversion Error -> {}", ExceptionUtils.getStackTrace(e));
            throw new ConversionException(e);
        }
    }
}
