package com.swiftmessage.api.entities.models;

import com.swiftmessage.api.entities.parse.SwiftDtoParser;

import java.util.Objects;

public record Swift7xxDto(String basicHeader, String applicationHeader, String userHeader, String text,
                          String trailerBlock) {

    public Swift7xxDto(Swift7xx swift7xx) {
        this(SwiftDtoParser.parseBasicHeader(swift7xx),
                SwiftDtoParser.parseApplicationHeader(swift7xx),
                SwiftDtoParser.parseUserHeader(swift7xx),
                SwiftDtoParser.parseText(swift7xx),
                SwiftDtoParser.trailerBlock(swift7xx));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (Objects.nonNull(basicHeader)) {
            sb.append(basicHeader);
        }
        if (Objects.nonNull(applicationHeader)) {
            sb.append(applicationHeader);
        }
        if (Objects.nonNull(userHeader)) {
            sb.append(userHeader);
        }
        if (Objects.nonNull(text)) {
            sb.append(text);
        }
        if (Objects.nonNull(trailerBlock)) {
            sb.append(trailerBlock);
        }
        return sb.toString();
    }
}
