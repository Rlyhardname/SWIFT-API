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
        referenceAndMac.setSenderReference(reference);
        return this;
    }

    public ReferenceAndMacBuilder addTransactionReference(String reference) {
        referenceAndMac.setTransactionReference(reference);
        return this;
    }

    public ReferenceAndMacBuilder addMacReference(String reference) {
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

    public ReferenceAndMac getCompositeKey() {
        return referenceAndMac;
    }
}
