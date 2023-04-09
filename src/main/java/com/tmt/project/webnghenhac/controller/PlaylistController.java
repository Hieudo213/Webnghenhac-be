package com.tmt.project.webnghenhac.controller;

import com.tmt.project.webnghenhac.domain.Playlist;
import com.tmt.project.webnghenhac.service.PlaylistService;
import com.tmt.project.webnghenhac.service.request.CreatePlaylistResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/playlists")
public class PlaylistController {
    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping("")
    public ResponseEntity<List<Playlist>> getAllActivatedPlaylist(){
        var playlists = this.playlistService.getAllActivatedPlaylist();
        return ResponseEntity.ok(playlists);
    }

    @GetMapping("/admin/all")
    public ResponseEntity<List<Playlist>> getAllPlaylist(){
        var playlists = this.playlistService.getAllPlaylist();
        return ResponseEntity.ok(playlists);
    }

    @GetMapping("/admin/in-process/all")
    public ResponseEntity<List<Playlist>> getAllInProcessPlaylist(){
        var playlists = this.playlistService.getAllInProcessPlaylist();
        return ResponseEntity.ok(playlists);
    }

    @GetMapping("/admin/done/all")
    public ResponseEntity<List<Playlist>> getAllDonePlaylist(){
        var playlists = this.playlistService.getAllDonePlaylist();
        return ResponseEntity.ok(playlists);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Playlist> getPlaylistById (@PathVariable("id") Integer id){
        var playlist = this.playlistService.getPlaylistById(id);
        return ResponseEntity.ok(playlist);
    }

    @PostMapping("/create")
    public ResponseEntity<Playlist> createNewPlaylist(@RequestBody CreatePlaylistResponse playlist){
        var newPlaylist = this.playlistService.createNewPlaylist(playlist);
        return ResponseEntity.ok(newPlaylist);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Playlist> updatePlaylist(@PathVariable("id") Integer id,
                                                   @RequestBody CreatePlaylistResponse playlist)
    {
        var updatePlaylist = this.playlistService.updatePlaylistById(id, playlist);
        return ResponseEntity.ok(updatePlaylist);
    }

    @PutMapping("update/picture/{id}")
    public ResponseEntity<Playlist> updatePictureForPlaylistById(@RequestParam("newfile") MultipartFile file,
                                                                 @PathVariable("id") Integer id) throws IOException {
        var playlist = this.playlistService.updatePictureForPlaylistById(id, file);
        return ResponseEntity.ok(playlist);
    }

    @PutMapping("admin/activate/{id}")
    public ResponseEntity<Playlist> activatePlaylistById (@PathVariable("id") Integer id){
        var playlist = this.playlistService.activatePlaylistById(id);
        return ResponseEntity.ok(playlist);
    }


}
