package com.tmt.project.webnghenhac.controller;

import com.tmt.project.webnghenhac.domain.Music;
import com.tmt.project.webnghenhac.repository.MusicRepository;
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
import java.util.List;

@RestController
@RequestMapping("api/v1/public/musics")
public class MusicController {
    private final MusicRepository musicRepository;

    public MusicController(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }

    @PostMapping("/file")
    public ResponseEntity<Music> multipartFormData(@RequestParam("newfile") MultipartFile file)throws IOException {
        String relativePath = System.getProperty("user.dir");
        System.out.println(relativePath);
        String savedFilePath = relativePath + "/Musics/" + file.getOriginalFilename();
        File savedFile = new File(savedFilePath);
        file.transferTo(savedFile);

        // Tạo bản ghi media
        Music newMusic = new Music();
        newMusic.setMusicName(file.getOriginalFilename());
        newMusic.setMusicURL(savedFilePath);
        newMusic.setUploadDate(LocalDate.now());
        newMusic.setMusicType(file.getContentType());
        var savedMusic = this.musicRepository.save(newMusic);
        return ResponseEntity.ok(savedMusic);
    }
    @GetMapping("/file/{id}")
    public ResponseEntity<org.springframework.core.io.Resource> getMediaById(@PathVariable("id") Integer id) throws MalformedURLException, FileNotFoundException {
        var musicFromDatabase = this.musicRepository.findById(id);

        if (musicFromDatabase.isPresent()){
            var realPicture = musicFromDatabase.get();
            Path pathToFile = Path.of(realPicture.getMusicURL());

            File file = new File(realPicture.getMusicURL());
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

    @GetMapping("")
    public ResponseEntity<List<Music>> getALlMusic(){
        var musics = this.musicRepository.findAll();
        return ResponseEntity.ok(musics);
    }

}
