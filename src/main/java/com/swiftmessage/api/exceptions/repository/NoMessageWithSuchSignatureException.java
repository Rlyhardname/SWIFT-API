package com.swiftmessage.api.exceptions.repository;

public class NoMessageWithSuchSignatureException extends RuntimeException{
    public NoMessageWithSuchSignatureException(String message) {
        super(message);
    }
}
