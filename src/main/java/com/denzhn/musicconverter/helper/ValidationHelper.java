package com.denzhn.musicconverter.helper;

import com.denzhn.musicconverter.exception.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

@Slf4j
public final class ValidationHelper {
    private ValidationHelper(){
        throw new UnsupportedOperationException();
    }

    public static <T> void validate(T objectToValidate) {
        Arrays.stream(objectToValidate.getClass().getDeclaredFields())
                .forEach(field -> {
                    try {
                        field.setAccessible(true);
                        if (Objects.isNull(field.get(objectToValidate))){
                            log.error("Field: [{}] is null.", field.getName());
                            throw new ValidationException(String.format("Field: [%s] is null.", field.getName()));
                        }
                        field.setAccessible(false);
                    } catch (IllegalAccessException e) {
                        throw new ValidationException(ExceptionUtils.getStackTrace(e));
                    }
                    finally {
                        field.setAccessible(false);
                    }
                });
    }

    public static void validateByteData(MultipartFile file) {
        try {
            if (file.getBytes().length <=0) {
                log.error("ByteData is not valid -> Data is null.");
                throw new ValidationException("ByteData is not valid -> Data is null.");
            }
        } catch (IOException e) {
            log.error("ValidationException -> {}", ExceptionUtils.getStackTrace(e));
            throw new ValidationException(e);
        }
    }

    public static void validateString(String str) {
        if (StringUtils.isEmpty(str)){
            log.error("String is not valid -> String is null or empty.");
            throw new ValidationException("String is not valid -> String is null or empty.");
        }
    }
}
