package com.swiftmessage.api.util;

import com.swiftmessage.api.entities.models.SenderAndTransactionReferenceAndMac;
import com.swiftmessage.api.exceptions.builder.InvalidMessageIdentificationException;
import com.swiftmessage.api.exceptions.messages.ExceptionMessage;

import java.util.Objects;

public class CompositeKeyBuilder {

    private SenderAndTransactionReferenceAndMac senderAndTransactionReferenceAndMac;

    public CompositeKeyBuilder(SenderAndTransactionReferenceAndMac senderAndTransactionReferenceAndMac) {
        this.senderAndTransactionReferenceAndMac = senderAndTransactionReferenceAndMac;
    }

    public void addSenderReference(String reference) {
        senderAndTransactionReferenceAndMac.setSenderReference(reference);
    }

    public void addTransactionReference(String reference) {
        senderAndTransactionReferenceAndMac.setTransactionReference(reference);
    }

    public void addMacReference(String reference) {
        senderAndTransactionReferenceAndMac.setMAC(reference);
    }

    public SenderAndTransactionReferenceAndMac build() throws InvalidMessageIdentificationException {
        if (isValid()) {
            return senderAndTransactionReferenceAndMac;
        }

        throw new InvalidMessageIdentificationException(ExceptionMessage.INVALID_ID);
    }

    protected boolean isValid() {
        if (Objects.isNull(senderAndTransactionReferenceAndMac.getSenderReference())) {
            return false;
        }
        if (Objects.isNull(senderAndTransactionReferenceAndMac.getTransactionReference())) {
            return false;
        }
        if (Objects.isNull(senderAndTransactionReferenceAndMac.getMAC())) {
            return false;
        }

        return true;
    }

    public SenderAndTransactionReferenceAndMac getCompositeKey() {
        return senderAndTransactionReferenceAndMac;
    }
}
