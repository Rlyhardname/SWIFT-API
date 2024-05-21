package com.swiftmessage.api.util;

import com.swiftmessage.api.entities.models.SenderAndTransactionReferenceAndMac;
import com.swiftmessage.api.exceptions.builder.InvalidMessageIdentificationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompositeKeyBuilderTest {
    public static final String MAC = "5:{MAC:00000000";
    public static final String SENDER_REFERENCE = ":20:67-C111111-KNTRL";
    public static final String TRANSACTION_REFERENCE = ":21:30-111-1111111";
    CompositeKeyBuilder builder;
    SenderAndTransactionReferenceAndMac expected;

    @BeforeEach
    void init() {
        SenderAndTransactionReferenceAndMac compositeKey;
        compositeKey = new SenderAndTransactionReferenceAndMac();
        builder = new CompositeKeyBuilder(compositeKey);
        expected = new SenderAndTransactionReferenceAndMac();
        expected.setSenderReference(SENDER_REFERENCE);
        expected.setTransactionReference(TRANSACTION_REFERENCE);
        expected.setMAC(MAC);
    }


    @Test
    @DisplayName("Missing sender reference, method throws exception")
    void throwInvalidMessageIdentificationExceptionMissingSenderReference() {
        builder.addTransactionReference(TRANSACTION_REFERENCE);
        builder.addMacReference(MAC);
        assertThrows(InvalidMessageIdentificationException.class, () -> {
            builder.build();
        });
    }

    @Test
    @DisplayName("Missing transaction reference, method throws exception")
    void throwInvalidMessageIdentificationExceptionMissingTransactionReference() {
        builder.addSenderReference(SENDER_REFERENCE);
        builder.addMacReference(MAC);
        assertThrows(InvalidMessageIdentificationException.class, () -> {
            builder.build();
        });
    }

    @Test
    @DisplayName("Missing MAC, method throws exception")
    void throwInvalidMessageIdentificationExceptionMissingMAC() {
        builder.addTransactionReference(TRANSACTION_REFERENCE);
        builder.addSenderReference(SENDER_REFERENCE);
        assertThrows(InvalidMessageIdentificationException.class, () -> {
            builder.build();
        });
    }

    @Test
    void buildReturnSenderAndTransactionReferenceAndMacObject() {
        builder.addSenderReference(SENDER_REFERENCE);
        builder.addTransactionReference(TRANSACTION_REFERENCE);
        builder.addMacReference(MAC);
        SenderAndTransactionReferenceAndMac actual = builder.build();

        assertEquals(expected, actual, "both objects should have equal value fields");
    }

    @Test
    @DisplayName("isValid returns true")
    void isValidReturnsTrue() {
        builder.addSenderReference(SENDER_REFERENCE);
        builder.addTransactionReference(TRANSACTION_REFERENCE);
        builder.addMacReference(MAC);
        boolean expected = true;
        assertEquals(expected, builder.isValid());
    }

    @Test
    @DisplayName("isValid returns false")
    void isValidReturnsFalseMissingMAC() {
        builder.addSenderReference(SENDER_REFERENCE);
        builder.addTransactionReference(TRANSACTION_REFERENCE);
        boolean expected = false;
        assertEquals(expected, builder.isValid());
    }

    @Test
    @DisplayName("return Compositekey object")
    void getSenderAndTransactionReferenceAndMacObject(){
        assertEquals(new SenderAndTransactionReferenceAndMac(),builder.getCompositeKey(), "should return save object as builder");
    }
}