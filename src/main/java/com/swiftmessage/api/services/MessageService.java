package com.swiftmessage.api.services;

import com.swiftmessage.api.entities.models.Swift7xx;
import com.swiftmessage.api.entities.models.Swift7xxDto;
import com.swiftmessage.api.exceptions.repository.NoMessageWithSuchSignatureException;
import com.swiftmessage.api.io.FileHandler;
import com.swiftmessage.api.io.Handler;
import com.swiftmessage.api.repositories.Swift7xxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
        if (message != null) {
            swift7xxRepository.save(message);
        }
    }

    public Swift7xxDto loadRecord(String senderReference, String transactionReference) {
        Swift7xx message = swift7xxRepository.findSwift7xxByReferenceAndMac(senderReference, transactionReference)
                .orElseThrow(() -> new NoMessageWithSuchSignatureException(MESSAGE_WITH_SIGNATURE_DOES_NOT_EXIST));
        return new Swift7xxDto(message);
    }


}
