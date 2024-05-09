package com.swiftmessage.api.state;

import com.swiftmessage.api.entities.builder.ReferenceAndMacBuilder;

public class MessageState {
    public static final String ONE = "{1:";
    public static final String TWO = "{2:";
    public static final String THREE = "{3:";
    public static final String FOUR = "{4:";
    public static final String FIVE = "{5:";
    public static final String SENDER_REFERENCE_STATE = ":20:";
    public static final String TRANSACTION_REFERENCE_STATE = ":21:";
    public static final String MAC_REFERENCE_STATE = "MAC:";
    private int state;

    public MessageState(int state) {
        this.state = state;
    }

    public static boolean isState(String state) {
        if (state.startsWith(MessageState.ONE) ||
                state.startsWith(MessageState.TWO) ||
                state.startsWith(MessageState.THREE) ||
                state.startsWith(MessageState.FOUR) ||
                state.startsWith(MessageState.FIVE)) {
            return true;
        }

        return false;
    }

    public static boolean isReferenceState(String state) {
        if (state.startsWith(SENDER_REFERENCE_STATE)
                || state.startsWith(TRANSACTION_REFERENCE_STATE)) {
            return true;
        }

        return false;
    }

    public static boolean isMacState(String state) {
        if (state.startsWith(FIVE+MAC_REFERENCE_STATE)) {
            return true;
        }

        return false;
    }

    public static void buildReferenceState(ReferenceAndMacBuilder builder, String... line) {
        if (line[0].startsWith(SENDER_REFERENCE_STATE)) {
            builder.addSenderReference(line[0]);
        }
        if (line[0].startsWith(TRANSACTION_REFERENCE_STATE)) {
            builder.addTransactionReference(line[0]);
        }
        if (line[1].startsWith(FIVE+MAC_REFERENCE_STATE)) {
            String appendLines = line[0] + line[1];
            builder.addMacReference(appendLines);
        }

    }

    public void changeState(String state) {
        this.state = findState(state);
    }

    private int findState(String state) {
        if (state.startsWith(MessageState.ONE)) {
            return 1;
        }

        if (state.startsWith(MessageState.TWO)) {
            return 2;
        }

        if (state.startsWith(MessageState.THREE)) {
            return 3;
        }

        if (state.startsWith(MessageState.FOUR)) {
            return 4;
        }

        if (state.startsWith(MessageState.FIVE)) {
            return 5;
        }

        return -1;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        this.state = state;
    }
}

