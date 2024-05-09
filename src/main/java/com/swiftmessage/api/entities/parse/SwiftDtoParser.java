package com.swiftmessage.api.entities.parse;

import com.swiftmessage.api.entities.models.Swift7xx;

public class SwiftDtoParser {
    public static String parseBasicHeader(Swift7xx swift7xx) {
        return "{" + swift7xx.getBasicHeader() + "}";
    }

    public static String parseApplicationHeader(Swift7xx swift7xx) {
        return "{" + swift7xx.getApplicationHeader() + "}";
    }

    public static String parseUserHeader(Swift7xx swift7xx) {
        return "{" + swift7xx.getUserHeader() + "}";
    }

    public static String parseText(Swift7xx swift7xx) {
        return "{" + swift7xx.getText() + "}";
    }

    public static String trailerBlock(Swift7xx swift7xx) {
        return "{" + swift7xx.getTrailerBlock() + "}";
    }

}
