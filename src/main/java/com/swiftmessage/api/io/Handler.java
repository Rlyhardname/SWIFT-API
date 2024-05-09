package com.swiftmessage.api.io;

import com.swiftmessage.api.entities.models.SwiftMessage;

public abstract class Handler<T>{
    private final MessageTools<T> messageTools;

    protected Handler(MessageTools<T> messageTools) {
        if (messageTools == null) {
            throw new NullPointerException();
        }
        this.messageTools = messageTools;
    }

    public abstract SwiftMessage readAndParseToSwiftMessage(T input);

    protected MessageTools<T> getMessageDetails() {
        return messageTools;
    }
}


