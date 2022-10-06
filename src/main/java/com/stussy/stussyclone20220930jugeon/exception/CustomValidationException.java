package com.stussy.stussyclone20220930jugeon.exception;

import lombok.Getter;

import java.util.Map;

@Getter
public class CustomValidationException extends RuntimeException {
    private Map<String, String> errorMap;

    public CustomValidationException(String message,  Map<String, String> errorMap){ //생성자
        super(message);
        this.errorMap = errorMap;
    }
}
