package com.tmt.project.webnghenhac.controller;

import com.tmt.project.webnghenhac.domain.Picture;
import com.tmt.project.webnghenhac.repository.PictureRepository;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.time.LocalDate;
@RestController
@RequestMapping("/api/v1/public/pictures")
public class PictureController {
    private final PictureRepository pictureRepository;


    public PictureController(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @PostMapping("/file")
    public ResponseEntity<Picture> multipartFormData(@RequestParam("newfile") MultipartFile file)throws IOException {
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
        return ResponseEntity.ok(savedPicture);
    }
    @GetMapping("/file/{id}")
    public ResponseEntity<org.springframework.core.io.Resource> getMediaById(@PathVariable("id") Integer id) throws MalformedURLException, FileNotFoundException {
        var pictureFromDatabase = this.pictureRepository.findById(id);

        if (pictureFromDatabase.isPresent()){
            var realPicture = pictureFromDatabase.get();
            Path pathToFile = Path.of(realPicture.getPictureURL());

            File file = new File(realPicture.getPictureURL());
            InputStream inputStream = new FileInputStream(file);
            InputStreamSource inputStreamSource = new InputStreamResource(inputStream);
            Resource resource = new UrlResource(pathToFile.toUri());
            if (resource.exists()){
                return ResponseEntity
                        .ok()
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                        .body(resource);
            }else {
                throw new RuntimeException("File not found");
            }
        }else {
            return null;
        }
    }

}
