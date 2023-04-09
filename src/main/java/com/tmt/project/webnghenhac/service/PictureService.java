package com.tmt.project.webnghenhac.service;

import com.tmt.project.webnghenhac.domain.Picture;
import com.tmt.project.webnghenhac.repository.PictureRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;


public class PictureService {
    private final PictureRepository pictureRepository;

    public PictureService(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    public Picture  multipartFormData( MultipartFile file)throws IOException{
        String relativePath = System.getProperty("user.dir");
        System.out.println(relativePath);
        String savedFilePath = relativePath + "/Pictures/" + file.getOriginalFilename();
        File savedFile = new File(savedFilePath);
        file.transferTo(savedFile);


        // Tạo bản ghi media
        Picture newPicture = new Picture();
        newPicture.setPictureName(file.getOriginalFilename());
        newPicture.setPictureURL(savedFilePath);
        newPicture.setUploadDate(LocalDate.now());
        var savedPicture = this.pictureRepository.save(newPicture);
        return savedPicture;
    }
}
