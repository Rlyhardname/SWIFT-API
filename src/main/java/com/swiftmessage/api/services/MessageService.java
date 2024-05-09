package com.swiftmessage.api.services;

import com.swiftmessage.api.entities.models.Swift7xx;
import com.swiftmessage.api.entities.models.Swift7xxDto;
import com.swiftmessage.api.exceptions.NoMessageWithSuchSignatureException;
import com.swiftmessage.api.io.FileHandler;
import com.swiftmessage.api.io.Handler;
import com.swiftmessage.api.repositories.Swift7xxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.swiftmessage.api.exceptions.messages.ExceptionMessage.MESSAGE_WITH_SIGNATURE_DOES_NOT_EXIST;

@Service
public class MessageService {
    private final Swift7xxRepository swift7xxRepository;

    @Autowired
    public MessageService(Swift7xxRepository swift7xxRepository) {
        this.swift7xxRepository = swift7xxRepository;
    }

    public void saveMessage(MultipartFile input) {
        Handler<MultipartFile> handler = FileHandler.instanceOf(input);
        Swift7xx message = (Swift7xx) handler.readAndParseToSwiftMessage(input);
        swift7xxRepository.save(message);
    }

    public Swift7xxDto loadRecord(String senderReference, String transactionReference) {
        Swift7xx message = swift7xxRepository.findSwift7xxByReferenceAndMac(senderReference, transactionReference)
                .orElseThrow(() -> new NoMessageWithSuchSignatureException(MESSAGE_WITH_SIGNATURE_DOES_NOT_EXIST));
        System.out.println(message);
        return new Swift7xxDto(message);
    }

    public void migrateMessageFromRepoToFileSystem(String senderReference, String transactionReference, String path, String fileName) {
        Swift7xx message = swift7xxRepository.findSwift7xxByReferenceAndMac(senderReference, transactionReference)
                .orElseThrow(() -> new NoMessageWithSuchSignatureException(MESSAGE_WITH_SIGNATURE_DOES_NOT_EXIST));
        try (BufferedWriter br = Files.newBufferedWriter(Path.of(path + "/" + fileName + ".txt"))) {
            Swift7xxDto dto = new Swift7xxDto(message);
            br.write(dto.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
