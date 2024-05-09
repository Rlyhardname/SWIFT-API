package com.swiftmessage.api.io;

@FunctionalInterface
public interface Reader<T> {
    String[] read(T input);
}
