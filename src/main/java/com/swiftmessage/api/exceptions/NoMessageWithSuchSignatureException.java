package com.swiftmessage.api.exceptions;

public class NoMessageWithSuchSignatureException extends RuntimeException{
    public NoMessageWithSuchSignatureException(String message) {
        super(message);
    }
}
