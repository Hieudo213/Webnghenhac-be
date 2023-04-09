package com.tmt.project.webnghenhac.service;

import com.tmt.project.webnghenhac.domain.Picture;
import com.tmt.project.webnghenhac.domain.Playlist;
import com.tmt.project.webnghenhac.repository.CategoryRepository;
import com.tmt.project.webnghenhac.repository.PictureRepository;
import com.tmt.project.webnghenhac.repository.PlaylistRepository;
import com.tmt.project.webnghenhac.repository.SongRepository;
import com.tmt.project.webnghenhac.service.request.CreatePlaylistResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class PlaylistService {
    private final PlaylistRepository playlistRepository;
    private final PictureRepository pictureRepository;
    private final CategoryRepository categoryRepository;
    private final SongRepository songRepository;


    public PlaylistService(PlaylistRepository playlistRepository, PictureRepository pictureRepository, CategoryRepository categoryRepository, SongRepository songRepository) {
        this.playlistRepository = playlistRepository;
        this.pictureRepository = pictureRepository;
        this.categoryRepository = categoryRepository;
        this.songRepository = songRepository;
    }

    public List<Playlist> getAllActivatedPlaylist() {
        var playlists = this.playlistRepository.getAllActivatedPlaylist();
        return playlists;
    }

    public List<Playlist> getAllInProcessPlaylist() {
        var playlists = this.playlistRepository.getAllInProcessPlaylist();
        return playlists;
    }

    public List<Playlist> getAllDonePlaylist() {
        var playlists = this.playlistRepository.getDonePlaylist();
        return playlists;
    }

    public List<Playlist> getAllPlaylist(){
        var playlists = this.playlistRepository.getAllPlaylist();
        return playlists;
    }

    public Playlist getPlaylistById(Integer id) {
        var checkPlaylist = this.playlistRepository.findById(id);
        if (!checkPlaylist.isPresent()) {
            throw new RuntimeException("Id not found");
        }
        var realPlaylist = checkPlaylist.get();

        return realPlaylist;
    }

    public Playlist createNewPlaylist(CreatePlaylistResponse playlistResponse) {
        var defaultPicture = this.pictureRepository.findById(42);
        var defaultCategory = this.categoryRepository.findById(0);
        var newPlaylist = new Playlist();
        newPlaylist.setTitle(playlistResponse.getTitle());
        newPlaylist.setDescription(playlistResponse.getDescription());
        newPlaylist.setPicture(defaultPicture.get());
        newPlaylist.setCategory(defaultCategory.get());
        newPlaylist.setIsValid(false);
        newPlaylist.setStatus(false);
        this.playlistRepository.save(newPlaylist);
        return newPlaylist;
    }

    public Playlist updatePlaylistById(Integer id, CreatePlaylistResponse playlistResponse){
        var checkedPlaylist = this.playlistRepository.findById(id);
        if (!checkedPlaylist.isPresent()){
            throw new RuntimeException("Id not found");
        }
        var realPlaylist =checkedPlaylist.get();
        realPlaylist.setTitle(playlistResponse.getTitle());
        realPlaylist.setDescription(playlistResponse.getDescription());
        realPlaylist.setIsValid(true);
        this.playlistRepository.save(realPlaylist);
        return realPlaylist;
    }

    public Playlist updatePictureForPlaylistById(Integer id, MultipartFile file) throws IOException{
        var checkPlaylist = this.playlistRepository.findById(id);
        if (!checkPlaylist.isPresent()) {
            throw new RuntimeException("Id not found");
        }
        var realPlaylist = checkPlaylist.get();
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
        realPlaylist.setPicture(savedPicture);
        realPlaylist.setIsValid(true);
        this.playlistRepository.save(realPlaylist);
        return realPlaylist;
    }

    public Playlist activatePlaylistById(Integer id){
        var checkPlaylist = this.playlistRepository.findById(id);
        if (!checkPlaylist.isPresent()) {
            throw new RuntimeException("Id not found");
        }
        var realPlaylist = checkPlaylist.get();
        realPlaylist.setStatus(true);
        realPlaylist.setIsValid(true);
        this.playlistRepository.save(realPlaylist);
        return realPlaylist;
    }
}
