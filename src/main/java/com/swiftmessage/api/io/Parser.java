package com.swiftmessage.api.io;

import com.swiftmessage.api.entities.models.SwiftMessage;

@FunctionalInterface
public interface Parser {
    SwiftMessage parse(String[] lines);
}