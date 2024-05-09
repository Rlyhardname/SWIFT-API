package com.swiftmessage.api.io;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileReader<T extends MultipartFile> implements Reader<T> {
    @Override
    public String[] read(T input) {
        {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(input.getInputStream()))) {
                return br.lines().map(String::valueOf).toArray(String[]::new);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
