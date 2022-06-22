package com.denzhn.musicconverter.exception;

public class ValidationException extends BaseException{
    public ValidationException(Throwable cause) {
        super("Validation Error -> ", cause);
    }

    public ValidationException(String message) {
        super(message, new Throwable());
    }
}
