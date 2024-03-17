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

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.donationmanagementsystem.exception.StorageException;
import com.donationmanagementsystem.exception.StorageFileNotFoundException;
import com.donationmanagementsystem.service.StorageService;
import com.donationmanagementsystem.utils.AppConstant;
import com.donationmanagementsystem.utils.Helper;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

@Service
public class StorageServiceImpl implements StorageService {

    @Value("${image.upload.path}")
    private String uploadPath;

    @Override
    public String uploadFile(MultipartFile file, String directory) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file.");
            }
            var destinationPath = uploadPath + "/" + directory;
            this.createDirectoryIfNotExist(destinationPath);
            Path path = Paths.get(destinationPath);
            var fileName = this.generateFileName(file);
            Path destinationFile = path.resolve(
                    Paths.get(fileName))
                    .normalize().toAbsolutePath();

            if (!destinationFile.getParent().equals(path.toAbsolutePath())) {
                // This is a security check
                throw new StorageException(
                        "Cannot store file outside current directory.");
            }

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
                if (AppConstant.IMAGE_EXTENSION.contains(this.getExtension(file)))
                    this.createthumbnail(destinationPath, fileName);
            }
            return fileName;
        } catch (IOException e) {
            throw new StorageException("Failed to store file.", e);
        }
    }

    @Override
    public void createthumbnail(String uploadedDirectory, String fileName) {
        try {
            Path path = Paths.get(uploadedDirectory);
            File file = new File(path + "/" + fileName);
            if (!file.exists()) {
                throw new StorageFileNotFoundException("File cannot be found");
            }
            this.createDirectoryIfNotExist(uploadedDirectory + "/thumb");
            var destinationDir = uploadedDirectory + "/thumb";
            Thumbnails.of(uploadedDirectory + "/" + fileName)
                    .size(AppConstant.THUMBNAIL_WIDTH, AppConstant.THUMBNAIL_LENGTH)
                    .toFiles(new File(destinationDir), Rename.NO_CHANGE);
        } catch (IOException e) {
            throw new StorageException("Failed to store file.", e);
        }
    }

    @Override
    public void deleteFile(String directory, String fileName) {
        File destinationPath = new File(uploadPath + "/" + directory + "/" + fileName);
        if (destinationPath.exists()) {
            File thumbnail = new File(uploadPath + "/" + directory + "/thumb/" + fileName);
            if (thumbnail.exists()) {
                thumbnail.delete();
            }
            destinationPath.delete();
        }
    }

    @Override
    public Path load(Path path, String fileName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'load'");
    }

    @Override
    public String generateFileName(MultipartFile file) {
        return Helper.getTimestamp() + Helper.getRandomToken(10) + "." + getExtension(file);
    }

    @Override
    public void createDirectoryIfNotExist(String directory) {
        try {
            File theDir = new File(directory);
            if (!theDir.exists()) {
                theDir.mkdirs();
            }
        } catch (Exception e) {
            throw new StorageException("Not a valid path", e);
        }
    }

    @Override
    public String getExtension(MultipartFile file) {
        return file.getOriginalFilename().split("\\.")[1];
    }

}
