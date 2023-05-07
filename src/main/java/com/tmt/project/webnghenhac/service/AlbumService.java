package com.tmt.project.webnghenhac.service;

import com.tmt.project.webnghenhac.domain.*;
import com.tmt.project.webnghenhac.repository.*;
import com.tmt.project.webnghenhac.service.request.CreateAlbumRequest;
import com.tmt.project.webnghenhac.service.request.UpdateAlbumRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final PictureRepository pictureRepository;

    private final ArtistRepository artistRepository;

    private final SongRepository songRepository;

    private final CategoryRepository categoryRepository;

    public AlbumService(AlbumRepository albumRepository, PictureRepository pictureRepository, ArtistRepository artistRepository, SongRepository songRepository, CategoryRepository categoryRepository) {
        this.albumRepository = albumRepository;
        this.pictureRepository = pictureRepository;
        this.artistRepository = artistRepository;
        this.songRepository = songRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Album> getAllActivatedAlbum(){
        List<Album> albums = this.albumRepository.getAllActivatedAlbums();
        return albums;
    }

    public List<Album> getAllDoneAlbum(){
        List<Album> albums = this.albumRepository.getAllDoneAlbum();
        return albums;
    }

    public Album getAlbumById(Integer id){
        var checkAlbum = this.albumRepository.findById(id);
        if (!checkAlbum.isPresent()){
            throw new RuntimeException("Album Not Found !");
        }
        var realAlbum = checkAlbum.get();
        return realAlbum;
    }

    public Album createAlbum(Album albumRequest)  {
        Album newAlbum = new Album();
        Optional<Picture> defaultPicture = this.pictureRepository.findById(42);
        Optional<Category> defaultCategory = this.categoryRepository.findById(0);
        newAlbum.setTitle(albumRequest.getTitle());
        newAlbum.setDescription(albumRequest.getDescription());
        newAlbum.setReleaseYear(albumRequest.getReleaseYear());
        newAlbum.setGenre(albumRequest.getGenre());
        newAlbum.setPublisher(albumRequest.getPublisher());
        newAlbum.setPicture(defaultPicture.get());
        newAlbum.setCategory(defaultCategory.get());
        newAlbum.setLength(albumRequest.getLength());
        newAlbum.getArtists().addAll(albumRequest
                .getArtists()
                .stream()
                .map(v ->{
                    Artist a = this.artistRepository.findById(v.getId()).get();
                    a.getAlbums().add(newAlbum);
                    return a;
                }).collect(Collectors.toList())
        );
        newAlbum.setIsValid(false);
        newAlbum.setStatus(false);
        this.albumRepository.save(newAlbum);
        return newAlbum;
    }

    public Album updateAlbumById(Integer id, UpdateAlbumRequest album){
        var checkedAlbum = this.albumRepository.findById(id);
        var updateCategory = this.categoryRepository.findById(album.getCategoryId());
        if (!checkedAlbum.isPresent()){
            throw new RuntimeException("Id not found");
        }
        var realAlbum = checkedAlbum.get();
        realAlbum.setTitle(album.getTitle());
        realAlbum.setDescription(album.getDescription());
        realAlbum.setReleaseYear(album.getReleaseYear());
        realAlbum.setGenre(album.getGenre());
        realAlbum.setLength(album.getLength());
        realAlbum.setCategory(updateCategory.get());
        realAlbum.setIsValid(true);
        this.albumRepository.save(realAlbum);
        return realAlbum;
    }

    public Album updateAlbumArtistById(Integer id, Album album){
        var checkedAlbum = this.albumRepository.findById(id);
        if (!checkedAlbum.isPresent()){
            throw new RuntimeException("Id not found");
        }
        var realAlbum = checkedAlbum.get();
        realAlbum.getArtists().addAll(album
                .getArtists()
                .stream()
                .map(v ->{
                    Artist a = this.artistRepository.findById(v.getId()).get();
                    a.getAlbums().add(realAlbum);
                    return a;
                }).collect(Collectors.toList())
        );
        this.albumRepository.save(realAlbum);
        return realAlbum;
    }

    public List<Album> getAllInProcessAlbums(){
        var albums = this.albumRepository.getAllInProcessAlbum();
        return albums;
    }

    public List<Album> getAlbumForAdmin(){
        var albums = this.albumRepository.findAll();
        return albums;
    }

    public Album updatePictureForAlbumById(Integer id, MultipartFile file) throws IOException {
        var checkedAlbum = this.albumRepository.findById(id);
        if (!checkedAlbum.isPresent()){
            throw new RuntimeException("id not found");
        }
        var realAlbum = checkedAlbum.get();
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
        realAlbum.setPicture(savedPicture);
        realAlbum.setIsValid(true);
        this.albumRepository.save(realAlbum);
        return realAlbum;
    }

    public Album activateAlbumById(Integer id){
        var checkAlbum = this.albumRepository.findById(id);
        if (!checkAlbum.isPresent()){
            throw new RuntimeException("Album Not Found !");
        }
        var realAlbum = checkAlbum.get();
        realAlbum.setStatus(true);
        realAlbum.setIsValid(true);
        this.albumRepository.save(realAlbum);
        return realAlbum;
    }
}
