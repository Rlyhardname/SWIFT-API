package com.swiftmessage.api.state;

public class MessageState {
    private int state;

    public MessageState(int state) {
        this.state = state;
    }

    public void changeState(String state) {
        this.state = findState(state);
    }

    private int findState(String state) {
        if (state.startsWith(MessageStates.ONE)) {
            return 1;
        }

        if (state.startsWith(MessageStates.TWO)) {
            return 2;
        }

        if (state.startsWith(MessageStates.THREE)) {
            return 3;
        }

        if (state.startsWith(MessageStates.FOUR)) {
            return 4;
        }

        if (state.startsWith(MessageStates.FIVE)) {
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

