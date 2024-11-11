package com.ra.base_project_md4.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class UploadService
{
    /**
     * 1. upload lên server ->
     *
     * 2. upload lên cloud ->
     * */
    @Value("${upload-path}")
    private String uploadPath;

    public String uploadFile(MultipartFile file) {
        File myFile = new File(uploadPath);
        if(!myFile.exists()) {
            myFile.mkdir();
        }

        String fileName = file.getOriginalFilename();

        try {
            FileCopyUtils.copy(file.getBytes(),new File(uploadPath + File.separator + fileName));
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        return fileName;
    }
}
