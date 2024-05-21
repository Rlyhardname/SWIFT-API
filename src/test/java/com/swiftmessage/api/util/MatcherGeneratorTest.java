package com.swiftmessage.api.util;

import com.swiftmessage.api.io.exceptions.EmptyMessageException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class MatcherGeneratorTest {
    String[] lines;
    String regex;

    @BeforeEach
    void init() {
        lines = new String[]{"hello,", "world"};
        regex = "^[\\w]+,[\\w]+";
    }

    @Test
    @DisplayName("matcher.group() should return hello,world")
    void generateReturnsHelloCommaWorldFromMatcherFindMatcherGroup() {
        Matcher expectedMatcher = Pattern.compile(regex).matcher(String.join("", lines));
        expectedMatcher.find();
        String expected = expectedMatcher.group();
        Matcher actualMatcher = MatcherGenerator.generate(lines, regex);
        actualMatcher.find();
        String actual = actualMatcher.group();
        assertEquals(expected, actual, "should return 'hello,world' from Matcher.Find(), Matcher.group()");
    }

    @Test
    @DisplayName("Throws IllegalStateException.class")
    void generateThrowIllegalStateException() {
        // shadows global regex var
        String regex = ",";
        Matcher matcher = Pattern.compile(regex).matcher(String.join("", lines));
        Class expected = IllegalStateException.class;
        assertThrows(expected, () -> matcher.group());
    }

    // TODO review this test, mock doesn't seem to add to code coverage
    @Test
    @Disabled
    @DisplayName("Throws PatternSyntaxException.class")
    void generateThrowPatternSyntaxException() {
        try (var mockedStatic = Mockito.mockStatic(MatcherGenerator.class)) {
            mockedStatic.when(() -> MatcherGenerator.generate(lines, "(")).thenThrow(new PatternSyntaxException("1","!",1));
            assertThrows(PatternSyntaxException.class, () -> MatcherGenerator.generate(lines, "("));
        }

    }

    @Test
    @DisplayName("Throws NullPointerException.class")
    void generateThrowNullPointerException() {
        assertThrows(NullPointerException.class, () -> MatcherGenerator.generate(lines, "("));
    }

    @Test
    @DisplayName("returned matcher.pattern() should equal ^[\\w]+,[\\w]+")
    void generatePatternEqualsPattern() {
        Matcher expectedMatcher = Pattern.compile(regex).matcher(String.join("", lines));
        Matcher actualMatcher = MatcherGenerator.generate(lines, regex);
        assertEquals(expectedMatcher.pattern().pattern(), actualMatcher.pattern().pattern());
    }

    @Test
    @DisplayName("returned matcher starting position should be equal")
    void generateMatcherStartingPositionEqual() {
        Matcher expectedMatcher = Pattern.compile(regex).matcher(String.join("", lines));
        Matcher actualMatcher = MatcherGenerator.generate(lines, regex);
        expectedMatcher.find();
        actualMatcher.find();
        assertEquals(expectedMatcher.start(), actualMatcher.start());
    }

    @Test
    @DisplayName("returned matcher end position should be equal")
    void generateMatcherEndPositionEqual() {
        Matcher expectedMatcher = Pattern.compile(regex).matcher(String.join("", lines));
        Matcher actualMatcher = MatcherGenerator.generate(lines, regex);
        expectedMatcher.find();
        actualMatcher.find();
        assertEquals(expectedMatcher.end(), actualMatcher.end());
    }


    @Test
    @DisplayName("Throws PatternFormatException.class")
    void constructPatternThrowPatternSyntaxException() {
        Class expected = PatternSyntaxException.class;
        assertThrows(expected, () -> MatcherGenerator.constructPattern("("), "expected result to break with regex '(' ");
    }

    @Test
    @DisplayName("Should return a valid pattern")
    void constructPattern() {
        Pattern expected = Pattern.compile(",");
        assertSame(expected.pattern(), MatcherGenerator.constructPattern(",").pattern(), "should return pattern of ',' ");
    }

    @Test
    @DisplayName("Should concatenate an array into a single String")
    void appendMessage() {
        String expected = "hello,world";

        assertEquals(expected, MatcherGenerator.appendMessage(lines), "expected result hello,world");
    }

    @Test
    @DisplayName("appendMessage() throws EmptyMessageException.class")
    void appendMessageThrowsEmptyMessageException() {
        String[] emptyMessage = new String[]{""};

        assertThrows(EmptyMessageException.class, () -> MatcherGenerator.appendMessage(emptyMessage), "should throw EmptyMessageException on emptyArray");
    }

    @Test
    @DisplayName("appendMessage() throws NullPointerException.class")
    void appendMessageThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> MatcherGenerator.appendMessage(null), "should throw NullPointer on emptyArray");
    }

    @Test
    @DisplayName("returns true if non empty lines>0")
    void isAtLeastOneLine() {
        assertTrue(MatcherGenerator.isAtLeastOneLine(lines), "should pass with 2 strings in array or even 1");
    }

    @Test
    @DisplayName("returns false on empty array")
    void isAtLeastOneLineReturnsFalseOnEmptyArray() {
        String[] empty = new String[0];
        assertFalse(MatcherGenerator.isAtLeastOneLine(empty), "should return false on empty array");
    }


}