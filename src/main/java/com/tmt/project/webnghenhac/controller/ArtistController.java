package com.tmt.project.webnghenhac.controller;

import com.tmt.project.webnghenhac.domain.Artist;
import com.tmt.project.webnghenhac.service.ArtistService;
import com.tmt.project.webnghenhac.service.request.CreateArtistResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/artists")
public class ArtistController {
    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping("")
    public ResponseEntity<List<Artist>> getAllArtist(){
        var artists = this.artistService.getAllActivatedArtist();
        return ResponseEntity.ok(artists);
    }

    @GetMapping("/admin/all")
    public ResponseEntity<List<Artist>> getArtistAll(){
        var artists = this.artistService.getAllArtist();
        return ResponseEntity.ok(artists);
    }

    @GetMapping("/admin/in-process/all")
    public ResponseEntity<List<Artist>> getAllInProcessArtist(){
        var artists = this.artistService.getAllInProcessArtist();
        return ResponseEntity.ok(artists);
    }

    @GetMapping("/admin/done/all")
    public ResponseEntity<List<Artist>> getAllDoneArtist(){
        var artists = this.artistService.getAllDoneArtist();
        return ResponseEntity.ok(artists);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Artist> getArtistById(@PathVariable("id") Integer id){
        var artist = this.artistService.getArtistById(id);
        return ResponseEntity.ok(artist);
    }

    @PostMapping("/create")
    public ResponseEntity<Artist> createNewArtist (@RequestBody CreateArtistResponse artistResponse){
        var newArtist = this.artistService.createNewArtist(artistResponse);
        return ResponseEntity.ok(newArtist);
    }

   @PutMapping("update/{id}")
    public ResponseEntity<Artist>updateArtistById(@PathVariable("id") Integer id,
                                                  @RequestBody CreateArtistResponse artistResponse)
   {
       var updateArtist = this.artistService.updateArtist(id, artistResponse);
       return ResponseEntity.ok(updateArtist);
   }

    @PutMapping("update/picture/{id}")
    public ResponseEntity<Artist> updatePictureForArtistById(@RequestParam("newfile") MultipartFile file,
                                                             @PathVariable("id") Integer id)throws IOException{
        var updateArtist = this.artistService.updatePictureForArtistById(file, id);
        return ResponseEntity.ok(updateArtist);
    }

    @PutMapping("admin/activate/{id}")
    public ResponseEntity<Artist> activateArtistById(@PathVariable("id") Integer id){
        var artist = this.artistService.activateArtistById(id);
        return ResponseEntity.ok(artist);
    }


}
