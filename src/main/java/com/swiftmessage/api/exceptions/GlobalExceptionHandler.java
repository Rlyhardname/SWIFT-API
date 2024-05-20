package com.swiftmessage.api.exceptions;

import com.swiftmessage.api.exceptions.builder.InvalidMessageIdentificationException;
import com.swiftmessage.api.exceptions.builder.MessageIdentifierDuplicationException;
import com.swiftmessage.api.exceptions.repository.NoMessageWithSuchSignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidMessageIdentificationException.class)
    public ResponseEntity<Object> handleInvalidMessageIdentificationException() {
        return new ResponseEntity<>("Incomplete swift message, missing identifier fields", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MessageIdentifierDuplicationException.class)
    public ResponseEntity<Object> handleMessageIdentifierDuplicationException() {
        return new ResponseEntity<>("Duplications in message found", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoMessageWithSuchSignatureException.class)
    public ResponseEntity<Object> handleNoMessageWithSuchSignatureException() {
        return new ResponseEntity<>("Message with requested signature doesn't exist!", HttpStatus.NOT_FOUND);
    }
}
