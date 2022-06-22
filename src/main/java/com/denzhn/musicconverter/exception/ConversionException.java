package com.denzhn.musicconverter.exception;

public class ConversionException extends BaseException{
    public ConversionException(Throwable cause) {
        super("Conversion Error -> ", cause);
    }

    public ConversionException(String message) {
        super(message, new Throwable());
    }
}
