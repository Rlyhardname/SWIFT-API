package com.swiftmessage.api.state;

import com.swiftmessage.api.exceptions.builder.MessageIdentifierDuplicationException;
import com.swiftmessage.api.util.CompositeKeyBuilder;

import java.util.Objects;

import static com.swiftmessage.api.io.exceptions.messages.CompositeKeyExceptions.*;

public class MessageUtils {
    public static boolean isState(String state) {
        return (state.startsWith(MessageStates.ONE) ||
                state.startsWith(MessageStates.TWO) ||
                state.startsWith(MessageStates.THREE) ||
                state.startsWith(MessageStates.FOUR) ||
                state.startsWith(MessageStates.FIVE));
    }

    public static boolean isReferenceState(String state) {
        return (state.startsWith(MessageStates.SENDER_REFERENCE_STATE)
                || state.startsWith(MessageStates.TRANSACTION_REFERENCE_STATE));
    }

    public static boolean isMacState(String state) {
        return (state.startsWith(MessageStates.FIVE + MessageStates.MAC_REFERENCE_STATE));
    }

    public static void buildReferenceState(CompositeKeyBuilder builder, String... line) throws MessageIdentifierDuplicationException {
        if (line[0].startsWith(MessageStates.SENDER_REFERENCE_STATE)) {
            String senderReference = line[0];
            if (Objects.nonNull(builder.getCompositeKey().getSenderReference())) {
                throw new MessageIdentifierDuplicationException(String.format(SENDER_REFERENCE_DUPLICATION, senderReference));
            }

            builder.addSenderReference(line[0]);
        }
        if (line[0].startsWith(MessageStates.TRANSACTION_REFERENCE_STATE)) {
            String transactionReference = line[0];
            if (Objects.nonNull(builder.getCompositeKey().getSenderReference())) {
                throw new MessageIdentifierDuplicationException(String.format(TRANSACTION_REFERENCE_DUPLICATION, transactionReference));
            }

            builder.addTransactionReference(line[0]);
        }
        if (line[0].startsWith(MessageStates.FIVE + MessageStates.MAC_REFERENCE_STATE)) {
            String mac = line[0].substring(4);
            if (Objects.nonNull(builder.getCompositeKey().getSenderReference())) {
                throw new MessageIdentifierDuplicationException(String.format(MAC_DUPLICATION, mac));
            }

            builder.addMacReference(mac);
        }

    }
}
