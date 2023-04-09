package com.tmt.project.webnghenhac.controller;

import com.tmt.project.webnghenhac.domain.Album;
import com.tmt.project.webnghenhac.service.AlbumService;
import com.tmt.project.webnghenhac.service.request.CreateAlbumRequest;
import com.tmt.project.webnghenhac.service.request.UpdateAlbumRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/albums")
public class AlbumController {
    public final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("")
    public ResponseEntity<List<Album>> getAllActivatedAlbum() {
        List<Album> albums = this.albumService.getAllActivatedAlbum();
        return ResponseEntity.ok(albums);
    }

    @GetMapping("/admin/all")
    public ResponseEntity<List<Album>> getAllAlbums(){
        var albums = this.albumService.getAlbumForAdmin();
        return ResponseEntity.ok(albums);
    }

    @GetMapping("/admin/in-process/all")
    public ResponseEntity<List<Album>> getAllInProcessAlbums(){
        var albums = this.albumService.getAllInProcessAlbums();
        return ResponseEntity.ok(albums);
    }

    @GetMapping("/admin/done/all")
    public ResponseEntity<List<Album>> getAllDoneAlbums(){
        var albums = this.albumService.getAllDoneAlbum();
        return ResponseEntity.ok(albums);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Album> getAlbumById(@PathVariable Integer id) {
        Album album = this.albumService.getAlbumById(id);
        return ResponseEntity.ok(album);
    }

    @PostMapping("create")
    public ResponseEntity<Album> createNewAlbum(@RequestBody Album albumRequest) {
        var newAlbum = this.albumService.createAlbum(albumRequest);
        return ResponseEntity.ok(newAlbum);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Album> updateAlbumById(@PathVariable("id") Integer id,
                                                 @RequestBody UpdateAlbumRequest albumRequest) {
        var updateAlbum = this.albumService.updateAlbumById(id, albumRequest);
        return ResponseEntity.ok(updateAlbum);
    }

    @PutMapping("update/artist/{id}")
    public ResponseEntity<Album> updateAlbumArtistById(@PathVariable("id") Integer id,
                                                       @RequestBody Album album) {
        var updateAlbum = this.albumService.updateAlbumArtistById(id, album);
        return ResponseEntity.ok(updateAlbum);
    }

   @PutMapping("update/picture/{id}")
    public ResponseEntity<Album> updatePictureForAlbumById(@RequestParam("newfile") MultipartFile file,
                                                           @PathVariable("id") Integer id) throws IOException{
        var album = this.albumService.updatePictureForAlbumById(id, file);
        return ResponseEntity.ok(album);
   }

   @PutMapping("admin/activate/{id}")
    public ResponseEntity<Album> activateAlbumById(@PathVariable("id") Integer id){
        var album = this.albumService.activateAlbumById(id);
        return ResponseEntity.ok(album);
   }
}
