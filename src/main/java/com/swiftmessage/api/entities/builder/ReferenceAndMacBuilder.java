package com.swiftmessage.api.entities.builder;

import com.swiftmessage.api.entities.models.ReferenceAndMac;
import com.swiftmessage.api.exceptions.*;
import com.swiftmessage.api.exceptions.messages.ExceptionMessage;

import java.util.Objects;

public class ReferenceAndMacBuilder {

    private ReferenceAndMac referenceAndMac;

    public ReferenceAndMacBuilder(ReferenceAndMac referenceAndMac) {
        this.referenceAndMac = referenceAndMac;
    }

    public ReferenceAndMacBuilder addSenderReference(String reference) {
        if (referenceAndMac.getSenderReference() != null) {
            throw new MessageIdentifierDuplicationException(ExceptionMessage.SENDER_REFERENCE_DUPLICATION);
        }
        referenceAndMac.setSenderReference(reference);
        return this;
    }

    public ReferenceAndMacBuilder addTransactionReference(String reference) {
        if (referenceAndMac.getTransactionReference() != null) {
            throw new MessageIdentifierDuplicationException(ExceptionMessage.TRANSACTION_REFERENCE_DUPLICATION);
        }
        referenceAndMac.setTransactionReference(reference);
        return this;
    }

    public ReferenceAndMacBuilder addMacReference(String reference) {
        if (referenceAndMac.getMAC() != null) {
            throw new MessageIdentifierDuplicationException(ExceptionMessage.MAC_REFERENCE_DUPLICATION);
        }

        referenceAndMac.setMAC(reference);
        return this;
    }

    public ReferenceAndMac build() {
        if (isValid()) {
            return referenceAndMac;
        }

        throw new InvalidMessageIdentificationException(ExceptionMessage.INVALID_ID);
    }

    private boolean isValid() {
        if (Objects.isNull(referenceAndMac.getSenderReference())) {
            return false;
        }
        if (Objects.isNull(referenceAndMac.getTransactionReference())) {
            return false;
        }
        if (Objects.isNull(referenceAndMac.getMAC())) {
            return false;
        }

        return true;
    }
}
