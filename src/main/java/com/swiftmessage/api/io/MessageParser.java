package com.swiftmessage.api.io;

import com.swiftmessage.api.state.MessageUtils;
import com.swiftmessage.api.util.SwiftMessageFactory;
import com.swiftmessage.api.entities.models.SenderAndTransactionReferenceAndMac;
import com.swiftmessage.api.entities.models.Swift7xx;
import com.swiftmessage.api.exceptions.builder.InvalidMessageIdentificationException;
import com.swiftmessage.api.exceptions.builder.MessageIdentifierDuplicationException;
import com.swiftmessage.api.io.exceptions.MessageParserException;
import com.swiftmessage.api.util.MatcherGenerator;

import java.util.regex.Matcher;

import static com.swiftmessage.api.io.exceptions.messages.ParserExceptions.MESSAGE_BUILD_ERROR;

public class MessageParser implements Parser {
    public static String SWIFT_PATTERN_799 = "(\\{[0-9]:[\\d\\w\\s {:.,-]*\\})(\\{[\\w]*:[\\d]*\\})*";
    private final MessageDetails details;

    public MessageParser(MessageDetails details) {
        this.details = details;
    }

    @Override
    public Swift7xx parse(String[] lines) {
        Matcher matcher = MatcherGenerator.generate(lines, SWIFT_PATTERN_799);
        runMatcher(matcher);
        return tryBuildSwiftMessage();
    }

    private void runMatcher(Matcher matcher) {
        while (matcher.find()) {
            var line = matcher.group(1);
            addDetails(line);
            appendMessage();
            includeToCompositeKeyBuilder(matcher, line);
            if (MessageUtils.isState(line)) {
                details.getMessageState().changeState(line);
            }

            if (isMessageStateValid()) {
                initializeMessageArrayCell();
            } else {
                appendToMessageArrayCell();
            }

            cleanOldDetails();
        }
    }

    private void addDetails(String line) {
        details.getStackOfLines().push(line);
    }

    private void appendMessage() {
        while (details.getStackOfLines().size() > 0) {
            details.getSb().append(details.getStackOfLines().pop());
            details.getSb().append('\n');
        }
    }

    private void includeToCompositeKeyBuilder(Matcher matcher, String line) {
        if (MessageUtils.isReferenceState(line)) {
            MessageUtils.buildReferenceState(details.getCompositeKeyBuilder(), line);
        }
        if (MessageUtils.isMacState(line)) {
            String group2 = matcher.group(2);
            MessageUtils.buildReferenceState(details.getCompositeKeyBuilder(), line, group2);
        }

    }

    private boolean isMessageStateValid() {
        return details.getArrayOfLines()[details.getMessageState().getState()] != null;
    }

    private void initializeMessageArrayCell() {
        details.getArrayOfLines()[details.getMessageState().getState()] = details.getSb().toString();
    }

    private void appendToMessageArrayCell() {
        details.getArrayOfLines()[details.getMessageState().getState()] += details.getSb().toString();
    }

    private void cleanOldDetails() {
        details.setSb(new StringBuilder());
    }

    private Swift7xx tryBuildSwiftMessage() {
        try {
            return buildSwiftMessage();
            // TODO figure out exception structure..
        } catch (InvalidMessageIdentificationException | MessageIdentifierDuplicationException e) {
            // log e...
            e.printStackTrace();
        }

        throw new MessageParserException(MESSAGE_BUILD_ERROR);
    }

    private Swift7xx buildSwiftMessage() {
        SenderAndTransactionReferenceAndMac compositeKey = details.getCompositeKeyBuilder().build();
        return SwiftMessageFactory.instanceOf(compositeKey, details.getArrayOfLines());
    }

}
