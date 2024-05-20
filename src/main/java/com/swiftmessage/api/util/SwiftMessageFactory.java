package com.swiftmessage.api.util;

import com.swiftmessage.api.entities.models.ReferenceAndMac;
import com.swiftmessage.api.entities.models.Swift7xx;

import java.util.Arrays;

public final class SwiftMessageFactory {

    public static Swift7xx instanceOf(ReferenceAndMac compositeKey, String[] fields) {
        String basicHeader = fields[1];
        String applicationHeader = fields[2];
        String userHeader = fields[3];
        String text = fields[4];
        String trailerBlock = fields[5];

//        if (Objects.isNull(basicHeader) || Objects.isNull(applicationHeader)) {
//            // throw missing header exception
//            return null;
//        }
        //System.out.println(Arrays.toString(fields));
        return new Swift7xx(compositeKey, basicHeader, applicationHeader, userHeader, text, trailerBlock);
    }
}
