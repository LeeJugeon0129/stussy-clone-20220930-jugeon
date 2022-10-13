package com.stussy.stussyclone20220930jugeon.exception;


public class CustomInternalServerErrorException extends RuntimeException {
    public CustomInternalServerErrorException(String message) {
        super(message);
    }
}
