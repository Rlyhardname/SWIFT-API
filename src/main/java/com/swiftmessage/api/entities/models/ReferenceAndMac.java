package com.swiftmessage.api.entities.models;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ReferenceAndMac implements Serializable {

    private String senderReference;
    private String transactionReference;
    private String MAC;

    protected ReferenceAndMac(String senderReference, String transactionReference, String MAC) {
        this.senderReference = senderReference;
        this.transactionReference = transactionReference;
        this.MAC = MAC;
    }

    public ReferenceAndMac() {

    }

    public String getSenderReference() {
        return senderReference;
    }

    public String getTransactionReference() {
        return transactionReference;
    }

    public String getMAC() {
        return MAC;
    }

    public void setSenderReference(String senderReference) {
        this.senderReference = senderReference;
    }

    public void setTransactionReference(String transactionReference) {
        this.transactionReference = transactionReference;
    }

    public void setMAC(String MAC) {
        this.MAC = MAC;
    }
}
