package com.swiftmessage.api.controllers;

import com.swiftmessage.api.entities.models.Swift7xxDto;
import com.swiftmessage.api.services.FileService;
import com.swiftmessage.api.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping(path = "api/")
public class Controller {
    private final MessageService messageService;
    private final FileService fileService;

    @Autowired
    public Controller(MessageService messageService, FileService fileService) {
        this.messageService = messageService;
        this.fileService = fileService;
    }

    @GetMapping(path = "")
    public void indexPage() {

    }

    @RequestMapping(path = "file/send/multipart",
            method = RequestMethod.POST
            , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void saveMultipartFileToRepo(@RequestPart MultipartFile file) {
        messageService.saveMessage(file);
    }

    @PostMapping(path = "file/load/record")
    public ResponseEntity<Swift7xxDto> loadMessageRecord(@RequestParam String senderReference,
                                                         @RequestParam String transactionReference) {
        return ResponseEntity.of(Optional.of(messageService.loadRecord(senderReference, transactionReference)));
    }
}
