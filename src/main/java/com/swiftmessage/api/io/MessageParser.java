package com.swiftmessage.api.io;

import com.swiftmessage.api.entities.factory.SwiftMessageFactory;
import com.swiftmessage.api.entities.models.ReferenceAndMac;
import com.swiftmessage.api.entities.models.Swift7xx;
import com.swiftmessage.api.exceptions.InvalidMessageIdentificationException;
import com.swiftmessage.api.exceptions.MessageIdentifierDuplicationException;
import com.swiftmessage.api.io.exceptions.EmptyMessageException;
import com.swiftmessage.api.io.exceptions.MessageParserException;
import com.swiftmessage.api.state.MessageState;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import static com.swiftmessage.api.io.exceptions.messages.ParserExceptions.*;

public class MessageParser implements Parser {
    private MessageDetails details;
    String SWIFT_PATTERN_7xx = "\\{?([\\d\\w]+?:?[\\w\\d-:,.  ]+)\\}?";
    String SWIFT_PATTERN_799 = "(\\{[0-9]:[\\d\\w\\s {:.,-]*\\})(\\{[\\w]*:[\\d]*\\})*";

    public MessageParser(MessageDetails details) {
        this.details = details;
    }

    @Override
    public Swift7xx parse(String[] lines) {
        Matcher matcher = getMatcher(lines);
        runMatcher(matcher);
        return tryBuildSwiftMessage();
    }

    private Matcher getMatcher(String[] lines) {
        String message = constructMessage(lines);
        Pattern pattern = constructPattern();
        return constructMatcher(pattern, message);
    }

    private String constructMessage(String[] lines) {
        return appendMessage(lines);
    }

    private String appendMessage(String[] lines) {
        if (lines != null) {
            if (isAtLeastOneLine(lines)) {
                return String.join("", lines);
            }

            throw new EmptyMessageException(EMPTY_MESSAGE);
        }

        throw new NullPointerException();
    }

    private boolean isAtLeastOneLine(String[] lines) {
        for (String line : lines) {
            if (!line.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private Pattern constructPattern() {
        try {
            return Pattern.compile(SWIFT_PATTERN_799);
        } catch (PatternSyntaxException e) {
            throw new MessageParserException(INVALID_REGEX);
        }

    }

    private Matcher constructMatcher(Pattern pattern, String message) {
        return pattern.matcher(message);
    }

    private void runMatcher(Matcher matcher) {
        while (matcher.find()) {
            var line = matcher.group(1);
            addDetails(line);
            appendMessage();
            try {
                includeToCompositeKeyBuilder(matcher, line);
            } catch (MessageIdentifierDuplicationException e) {
                // log something is wrong with the message and maybe save in corrupted messages table?
            }

            if (MessageState.isState(line)) {
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

    private void addDetails(String line){
        details.getStackOfLines().push(line);
    }

    private void appendMessage() {
        while (details.getStackOfLines().size() > 0) {
            details.getSb().append(details.getStackOfLines().pop());
            details.getSb().append('\n');
        }
    }

    private void includeToCompositeKeyBuilder(Matcher matcher, String line) {
        if (MessageState.isReferenceState(line)) {
            MessageState.buildReferenceState(details.getCompositeKeyBuilder(), line);
        }
        if (MessageState.isMacState(line)) {
            String group2 = matcher.group(2);
            MessageState.buildReferenceState(details.getCompositeKeyBuilder(), line, group2);
        }

    }

    private boolean isMessageStateValid() {
        if (details.getArrayOfLines()[details.getMessageState().getState()] != null) {
            return true;
        }

        return false;
    }

    private void initializeMessageArrayCell() {
        details.getArrayOfLines()[details.getMessageState().getState()] = details.getSb().toString();
    }

    private void appendToMessageArrayCell() {
        details.getArrayOfLines()[details.getMessageState().getState()] += details.getSb().toString();
    }

    private void cleanOldDetails(){
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
        ReferenceAndMac compositeKey = details.getCompositeKeyBuilder().build();
        Swift7xx swiftMessage = SwiftMessageFactory.instanceOf(compositeKey, details.getArrayOfLines());
        return swiftMessage;
    }

}