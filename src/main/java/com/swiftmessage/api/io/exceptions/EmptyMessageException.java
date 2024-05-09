package com.swiftmessage.api.io.exceptions;

public class EmptyMessageException extends RuntimeException {
    public EmptyMessageException(String message) {
        super(message);
    }
}
