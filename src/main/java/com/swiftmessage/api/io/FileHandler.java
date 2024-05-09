package com.swiftmessage.api.io;

import com.swiftmessage.api.entities.models.Swift7xx;
import com.swiftmessage.api.io.exceptions.MissingFileException;
import org.springframework.web.multipart.MultipartFile;

import static com.swiftmessage.api.io.exceptions.messages.FileExceptions.MISSING_FILE;

public class FileHandler<T extends MultipartFile> extends Handler<T> {

    private FileHandler(MessageTools<T> messageTools) {
        super(messageTools);
    }

    public static <T extends MultipartFile> FileHandler<T> instanceOf(T input) {
        new NullPointerException();
        return new FileHandler<>(new MessageTools<>(new FileReader<>(), new MessageParser(new MessageDetails()), input));
    }

    @Override
    public Swift7xx readAndParseToSwiftMessage(T input) {
        String[] lines = getFileLines(input);
        return parseToSwift(lines);
    }

    private String[] getFileLines(T input) {
        Reader<T> reader = getMessageDetails().getReader();
        String[] lines = reader.read(input);
        if (isMultiline(lines)) {
            return lines;

        }

        if (isInputEmptyOrNull(lines)) {
            throw new MissingFileException(String.format(MISSING_FILE, input.getName()));
        }

        return lines;
    }

    private boolean isMultiline(String[] lines) {
        if (lines.length > 0) {
            return true;
        }

        return false;
    }

    private boolean isInputEmptyOrNull(String[] lines) {
        if (lines[0] == null || lines[0].isEmpty()) {
            return true;
        }

        return false;
    }

    private Swift7xx parseToSwift(String[] lines) {
        Parser parser = getMessageDetails().getParser();
        return (Swift7xx) parser.parse(lines);
    }

}
