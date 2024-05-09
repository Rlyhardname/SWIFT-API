package com.swiftmessage.api.io.exceptions;

public class MissingFileException extends RuntimeException {
    public MissingFileException(String message) {
        super(message);
        if(message == null){
            throw new NullPointerException();
        }

    }
}
