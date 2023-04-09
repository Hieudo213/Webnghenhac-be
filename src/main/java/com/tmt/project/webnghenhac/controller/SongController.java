package com.tmt.project.webnghenhac.controller;

import com.tmt.project.webnghenhac.domain.Song;
import com.tmt.project.webnghenhac.service.SongService;
import com.tmt.project.webnghenhac.service.request.CreateSongRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/public/songs")
public class SongController {
    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping("")
    public ResponseEntity<List<Song>> getAllActivatedSong(){
        var songs = this.songService.getAllActivatedSong();
        return ResponseEntity.ok(songs);
    }

    @GetMapping("/admin/all")
    public ResponseEntity<List<Song>> getAllSong(){
        var songs = this.songService.getAllSongs();
        return ResponseEntity.ok(songs);
    }

    @GetMapping("/admin/in-process/all")
    public ResponseEntity<List<Song>> getAllInProcessSong(){
        var songs = this.songService.getAllInProcessSong();
        return ResponseEntity.ok(songs);
    }

    @GetMapping("/admin/done/all")
    public ResponseEntity<List<Song>> getAllDoneSong(){
        var songs = this.songService.getAllDoneSong();
        return ResponseEntity.ok(songs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Song> getSongById(@PathVariable("id") Integer id){
        var result = this.songService.getSongById(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/create")
    public ResponseEntity<Song> createNewSong(@RequestBody CreateSongRequest song){
        var newSong  = this.songService.createNewSong(song);
        return ResponseEntity.ok(newSong);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Song> updateNewSong(@PathVariable("id") Integer id,@RequestBody CreateSongRequest song){
        var updatedSong = this.songService.updateSongById(id, song);
        return ResponseEntity.ok(updatedSong);
    }

    @PutMapping("admin/activate/{id}")
    public ResponseEntity<Song> activateSongById(@PathVariable("id") Integer id){
        var result = this.songService.activateSongById(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("update/artist/{id}")
    public ResponseEntity<Song> updateArtistForSongById(@PathVariable("id") Integer id, @RequestBody Song song){
        var updateSong = this.songService.updateSongArtistById(id, song);
        return ResponseEntity.ok(updateSong);
    }
}
