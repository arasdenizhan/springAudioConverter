package com.denzhn.musicconverter.exception;

public abstract class BaseException extends RuntimeException{
    protected BaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
