package com.denzhn.musicconverter.enums;

public enum SoundType {
    AIFF("aiff"),
    AU("au"),
    WAV("wav");
    private final String data;

    SoundType(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public static SoundType getFormat(String str){
        for(SoundType soundType : SoundType.values()){
            if(soundType.getData().equals(str)){
                return soundType;
            }
        }
        return null;
    }
}
