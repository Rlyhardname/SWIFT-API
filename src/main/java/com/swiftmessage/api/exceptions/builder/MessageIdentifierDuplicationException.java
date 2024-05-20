package com.swiftmessage.api.exceptions.builder;

public class MessageIdentifierDuplicationException extends RuntimeException {
    public MessageIdentifierDuplicationException(String message) {
        super(message);
    }
}
