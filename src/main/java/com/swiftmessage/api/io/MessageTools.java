package com.swiftmessage.api.io;

class MessageTools<T> {
    private final Reader<T> reader;
    private final Parser parser;
    private final T input;

    protected MessageTools(Reader<T> reader, Parser parser, T input) {
        this.reader = reader;
        this.parser = parser;
        this.input = input;
    }

    public Reader<T> getReader() {
        return reader;
    }

    public Parser getParser() {
        return parser;
    }

    public T getInput() {
        return input;
    }

}
