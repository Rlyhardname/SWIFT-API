package com.swiftmessage.api.io;

import com.swiftmessage.api.entities.builder.ReferenceAndMacBuilder;
import com.swiftmessage.api.entities.models.ReferenceAndMac;
import com.swiftmessage.api.state.MessageState;

import java.util.ArrayDeque;

class MessageDetails {
    private MessageState messageState;
    private StringBuilder sb;
    private ArrayDeque<String> stackOfLines;
    private String[] arrayOfLines;
    private ReferenceAndMacBuilder compositeKeyBuilder;

    public MessageDetails() {
        messageState = new MessageState(0);
        sb = new StringBuilder();
        stackOfLines = new ArrayDeque<>();
        arrayOfLines = new String[6];
        compositeKeyBuilder = new ReferenceAndMacBuilder(new ReferenceAndMac());
    }

    public void setSb(StringBuilder sb) {
        this.sb = sb;
    }

    public MessageState getMessageState() {
        return messageState;
    }

    public StringBuilder getSb() {
        return sb;
    }

    public ArrayDeque<String> getStackOfLines() {
        return stackOfLines;
    }

    public String[] getArrayOfLines() {
        return arrayOfLines;
    }

    public ReferenceAndMacBuilder getCompositeKeyBuilder() {
        return compositeKeyBuilder;
    }
}
