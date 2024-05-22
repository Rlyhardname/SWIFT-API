package com.swiftmessage.api.entities.models;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SenderAndTransactionReferenceAndMac implements Serializable {

    private String senderReference;
    private String transactionReference;
    private String MAC;

    protected SenderAndTransactionReferenceAndMac(String senderReference, String transactionReference, String MAC) {
        this.senderReference = senderReference;
        this.transactionReference = transactionReference;
        this.MAC = MAC;
    }

    public SenderAndTransactionReferenceAndMac() {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SenderAndTransactionReferenceAndMac that = (SenderAndTransactionReferenceAndMac) o;
        return Objects.equals(getSenderReference(), that.getSenderReference()) && Objects.equals(getTransactionReference(), that.getTransactionReference()) && Objects.equals(getMAC(), that.getMAC());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSenderReference(), getTransactionReference(), getMAC());
    }
}
