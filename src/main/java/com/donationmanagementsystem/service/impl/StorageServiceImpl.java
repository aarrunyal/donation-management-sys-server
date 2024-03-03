package com.donationmanagementsystem.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.donationmanagementsystem.exception.StorageException;
import com.donationmanagementsystem.service.StorageService;

@Service
public class StorageServiceImpl implements StorageService {

    private static final String UPLOAD_PATH = "src/main/resources/uploads";

    @Override
    public void uploadFile(MultipartFile file, String directory) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file.");
            }

            this.createDirectoryIfNotExist(directory);

            Path path = Paths.get(UPLOAD_PATH + "/" + directory);

            Path destinationFile = path.resolve(
                    Paths.get(this.generateFileName()))
                    .normalize().toAbsolutePath();

            if (!destinationFile.getParent().equals(path.toAbsolutePath())) {
                // This is a security check
                throw new StorageException(
                        "Cannot store file outside current directory.");
            }

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
            }

        } catch (IOException e) {
            throw new StorageException("Failed to store file.", e);
        }
    }

    @Override
    public void createthumbnail(String path, String fileName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createthumbnail'");
    }

    @Override
    public void deleteFile(String path, String fileName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteFile'");
    }

    @Override
    public Path load(Path path, String fileName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'load'");
    }

    @Override
    public String generateFileName() {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        return timeStamp;

    }

    @Override
    public void createDirectoryIfNotExist(String directory) {
        try {
            File theDir = new File(UPLOAD_PATH);
            if (!theDir.exists()) {
                theDir.mkdirs();
            }

            theDir = new File(UPLOAD_PATH + "/" + directory);
            if (!theDir.exists()) {
                theDir.mkdirs();
            }
        } catch (Exception e) {
            throw new StorageException("Not a valid path", e);
        }
    }

}
