package com.swiftmessage.api.util;

import com.swiftmessage.api.entities.models.SenderAndTransactionReferenceAndMac;
import com.swiftmessage.api.entities.models.Swift7xx;

public final class SwiftMessageFactory {

    public static Swift7xx instanceOf(SenderAndTransactionReferenceAndMac compositeKey, String[] fields) {
        String basicHeader = fields[1];
        String applicationHeader = fields[2];
        String userHeader = fields[3];
        String text = fields[4];
        String trailerBlock = fields[5];

        return new Swift7xx(compositeKey, basicHeader, applicationHeader, userHeader, text, trailerBlock);
    }
}
