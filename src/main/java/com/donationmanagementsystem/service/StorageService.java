package com.donationmanagementsystem.service;

import java.nio.file.Path;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    public void uploadFile(MultipartFile file, String path);

    public void createthumbnail(String path, String fileName);

    public void deleteFile(String path, String fileName);

    public Path load(Path path, String fileName);

    public String generateFileName();

    public void createDirectoryIfNotExist(String directory);

}
