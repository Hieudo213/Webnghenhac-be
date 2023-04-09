package com.tmt.project.webnghenhac.service;

import com.tmt.project.webnghenhac.domain.Album;
import com.tmt.project.webnghenhac.domain.Artist;
import com.tmt.project.webnghenhac.domain.Picture;
import com.tmt.project.webnghenhac.domain.Song;
import com.tmt.project.webnghenhac.repository.AlbumRepository;
import com.tmt.project.webnghenhac.repository.ArtistRepository;
import com.tmt.project.webnghenhac.repository.PictureRepository;
import com.tmt.project.webnghenhac.repository.SongRepository;
import com.tmt.project.webnghenhac.service.request.CreateArtistResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {
    private final ArtistRepository artistRepository;
    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;
    private final PictureRepository pictureRepository;

    public ArtistService(ArtistRepository artistRepository, SongRepository songRepository, AlbumRepository albumRepository, PictureRepository pictureRepository) {
        this.artistRepository = artistRepository;
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
        this.pictureRepository = pictureRepository;
    }

    public List<Artist> getAllActivatedArtist(){
        List<Artist> artists = this.artistRepository.getActivedArtist();
        return artists;
    }

    public Artist getArtistById(Integer id){
        Optional<Artist> checkedArtist = this.artistRepository.findById(id);
        if (!checkedArtist.isPresent()){
            throw new RuntimeException("Id not found!");
        }
        Artist realArtist = checkedArtist.get();
        return realArtist;
    }
    public Artist createNewArtist(CreateArtistResponse artistResponse){
       Optional<Picture>defaultPicture = this.pictureRepository.findById(42);
       Artist newArtist = new Artist();
       newArtist.setId(artistResponse.getId());
       newArtist.setName(artistResponse.getName());
       newArtist.setDescription(artistResponse.getDescription());
       newArtist.setDateOfBirth(artistResponse.getDateOfBirth());
       newArtist.setPlaceOfBirth(artistResponse.getPlaceOfBirth());
       newArtist.setPicture(defaultPicture.get());
       newArtist.setStatus(false);
       newArtist.setIsValid(false);
       this.artistRepository.save(newArtist);
       return newArtist;
    }

    public Artist updateArtist(Integer id,CreateArtistResponse artistResponse ){
       var checkedArtist = this.artistRepository.findById(id);
       if (!checkedArtist.isPresent()){
           throw new RuntimeException("Id not found");
       }
       var realArtist = checkedArtist.get();
       realArtist.setName(artistResponse.getName());
       realArtist.setDescription(artistResponse.getDescription());
       realArtist.setDateOfBirth(artistResponse.getDateOfBirth());
       realArtist.setPlaceOfBirth(artistResponse.getPlaceOfBirth());
       realArtist.setIsValid(true);
       this.artistRepository.save(realArtist);
       return realArtist;
    }

    public Artist updatePictureForArtistById(MultipartFile file, Integer id)throws IOException{
        var checkedArtist = this.artistRepository.findById(id);
        if (!checkedArtist.isPresent()){
            throw new RuntimeException("Id not found");
        }
        var realArtist = checkedArtist.get();
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
        realArtist.setPicture(savedPicture);
        realArtist.setIsValid(true);
        var savedArtist = this.artistRepository.save(realArtist);
        return savedArtist;
    }

    public List<Artist> getAllArtist(){
        var artists = this.artistRepository.findAll();
        return artists;
    }

    public List<Artist> getAllInProcessArtist(){
        var artists = this.artistRepository.getAllInProcessArtist();
        return artists;
    }

    public List<Artist> getAllDoneArtist(){
        var artists = this.artistRepository.getAllDoneArtist();
        return artists;
    }

    public Artist activateArtistById(Integer id){
        Optional<Artist> checkedArtist = this.artistRepository.findById(id);
        if (!checkedArtist.isPresent()){
            throw new RuntimeException("Id not found!");
        }
        Artist realArtist = checkedArtist.get();
        realArtist.setStatus(true);
        realArtist.setIsValid(true);
        this.artistRepository.save(realArtist);
        return realArtist;
    }
}
