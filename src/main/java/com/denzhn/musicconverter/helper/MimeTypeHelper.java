package com.denzhn.musicconverter.helper;

import com.denzhn.musicconverter.enums.SoundType;
import org.springframework.util.MimeTypeUtils;

public final class MimeTypeHelper {
    private MimeTypeHelper(){
        throw new UnsupportedOperationException();
    }

    public static String getMimeType(SoundType soundType){
        switch (soundType) {
            case WAV -> {
                return "audio/x-wav";
            }
            case AU -> {
                return "audio/basic";
            }
            case AIFF -> {
                return "audio/x-aiff";
            }
            default -> {
                return MimeTypeUtils.APPLICATION_OCTET_STREAM_VALUE;
            }
        }
    }
}
