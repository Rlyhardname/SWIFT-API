package com.swiftmessage.api.exceptions;

public class MessageIdentifierDuplicationException extends RuntimeException {
    public MessageIdentifierDuplicationException(String message) {
        super(message);
    }
}
