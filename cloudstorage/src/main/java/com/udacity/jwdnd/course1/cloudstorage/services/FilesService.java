package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.File;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.List;

@Service
public class FilesService {

    private FileMapper fileMapper;

    public FilesService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public int createFile(File fileData) {
        File dbFile = this.fileMapper.getFileByName(fileData.getFileName(), fileData.getUserId());
        if (dbFile != null) {
            throw new ConstraintViolationException(fileData.getFileName() + " file already exists. ", null);
        }
        return this.fileMapper.insertFile(fileData);
    }

    public List<File> getFilesByUserId(Integer userId) {
        return this.fileMapper.getFilesByUserId(userId);
    }

    public int deleteFileById(Integer fileId, Integer userId) {
        return this.fileMapper.deleteFileById(fileId, userId);
    }

    public File getFileById(Integer fileId, Integer userId) {
        return this.fileMapper.getFileById(fileId, userId);
    }
}
