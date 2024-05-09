package com.swiftmessage.api.services;

import com.swiftmessage.api.repositories.Swift7xxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileService {
    private final Swift7xxRepository swift7xxRepository;

    @Autowired
    public FileService(Swift7xxRepository swift7xxRepository) {
        this.swift7xxRepository = swift7xxRepository;
    }



}
