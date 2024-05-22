package com.swiftmessage.api.io;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderTest {

    public static Reader multipartFileReader;
    public static Class<?> classType;
    TestInfo testInfo;
    TestReporter testReporter;

    @BeforeEach
    void initObjects(TestInfo testInfo, TestReporter testReporter) {
        multipartFileReader = new FileReader<>();
        classType = MultipartFile.class;
        this.testInfo = testInfo;
        this.testReporter = testReporter;

    }

    @RepeatedTest(5)
    void repeatTest() {

    }

    @Test
    void isFileInputTypeMultipartFile() {
        assertSame(MultipartFile.class, classType);
    }

    @Test
    void throwClassCastExceptionWithNonMultipartFileType() {
        assertThrows(ClassCastException.class, () -> {
            multipartFileReader.read(5);
        });
    }

    @Nested
    class NestedClass {

        @Test
        void test1() {
            assertAll(
                    () -> assertTrue(true, () -> "if is true fails"),
                    () -> assertFalse(false, () -> "if is false fails")
            );
        }

        void test2() {

        }

    }

    @Test
   // @Disabled
    @DisplayName("Throw NullPointerException.class")
    void throwIOException() {
        MultipartFile actual = Mockito.mock(MultipartFile.class);
        assertThrows(NullPointerException.class, () -> {
            multipartFileReader.read(actual);
        });
    }

}