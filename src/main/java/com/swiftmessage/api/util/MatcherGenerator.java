package com.swiftmessage.api.util;

import com.swiftmessage.api.io.exceptions.EmptyMessageException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import static com.swiftmessage.api.io.exceptions.messages.ParserExceptions.EMPTY_MESSAGE;

public class MatcherGenerator {
    /**
     * @param lines
     * @param pattern
     * @return Instance of Matcher class or null if pattern is corrupted.
     * @throws EmptyMessageException
     */
    public static Matcher generate(String[] lines, String pattern) throws EmptyMessageException, NullPointerException {
        return getMatcher(lines,pattern);
    }

    private static Matcher getMatcher(String[] lines, String patternArg) {
        try {
            String message = constructMessage(lines);
            Pattern pattern = constructPattern(patternArg);
            return constructMatcher(pattern, message);
        } catch (PatternSyntaxException e) {
            //log(e);
            //alert system that pattern is corrupted and exit endpoint/use global exception handling for that maybe?
        }

        return null;
    }

    private static String constructMessage(String[] lines) {
        return appendMessage(lines);
    }

    private static String appendMessage(String[] lines) {
        if (lines != null) {
            if (isAtLeastOneLine(lines)) {
                return String.join("", lines);
            }

            throw new EmptyMessageException(EMPTY_MESSAGE);
        }

        throw new NullPointerException();
    }

    private static boolean isAtLeastOneLine(String[] lines) {
        for (String line : lines) {
            if (!line.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private static Pattern constructPattern(String pattern) {
        return Pattern.compile(pattern);
    }

    private static Matcher constructMatcher(Pattern pattern, String message) {
        return pattern.matcher(message);
    }
}
