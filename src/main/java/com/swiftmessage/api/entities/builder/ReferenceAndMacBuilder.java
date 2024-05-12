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

    public void addSenderReference(String reference) {
        referenceAndMac.setSenderReference(reference);
    }

    public void addTransactionReference(String reference) {
        referenceAndMac.setTransactionReference(reference);
    }

    public void addMacReference(String reference) {
        referenceAndMac.setMAC(reference);
    }

    public ReferenceAndMac build() throws InvalidMessageIdentificationException{
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

    public ReferenceAndMac getCompositeKey() {
        return referenceAndMac;
    }
}
