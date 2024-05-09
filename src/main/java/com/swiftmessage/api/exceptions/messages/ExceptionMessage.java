package com.swiftmessage.api.exceptions.messages;

public final class ExceptionMessage {
    public static final String SENDER_REFERENCE_DUPLICATION = "Sender reference already recorded in the current message!";
    public static final String TRANSACTION_REFERENCE_DUPLICATION = "Transaction reference already recorded in the current message!";
    public static final String MAC_REFERENCE_DUPLICATION = "MAC reference already recorded in the current message!";
    public static final String INVALID_ID = "Part of the identification is missing or corrupted!";
    public static final String MESSAGE_WITH_SIGNATURE_DOES_NOT_EXIST = "A message with such a SenderReference or Transaction reference does not exist!";
}
