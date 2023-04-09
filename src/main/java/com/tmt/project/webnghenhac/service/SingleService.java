package com.tmt.project.webnghenhac.service;

import com.tmt.project.webnghenhac.domain.Category;
import com.tmt.project.webnghenhac.domain.Picture;
import com.tmt.project.webnghenhac.domain.Single;
import com.tmt.project.webnghenhac.domain.Song;
import com.tmt.project.webnghenhac.repository.CategoryRepository;
import com.tmt.project.webnghenhac.repository.PictureRepository;
import com.tmt.project.webnghenhac.repository.SingleRepository;
import com.tmt.project.webnghenhac.repository.SongRepository;
import com.tmt.project.webnghenhac.service.request.CreateSingleResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SingleService {
    private final SingleRepository singleRepository;
    private final PictureRepository pictureRepository;
    private final SongRepository songRepository;
    private final CategoryRepository categoryRepository;


    public SingleService(SingleRepository singleRepository, PictureRepository pictureRepository, SongRepository songRepository, CategoryRepository categoryRepository) {
        this.singleRepository = singleRepository;
        this.pictureRepository = pictureRepository;
        this.songRepository = songRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Single> getAllSingles(){
        var singles = this.singleRepository.findAll();
        return singles;
    }

    public List<Single> getAllActivatedSingles(){
        var singles = this.singleRepository.getAllActivatedSingle();
        return singles;
    }

    public List<Single> getAllInProcessSingles(){
        var singles = this.singleRepository.getAllInProcessSingle();
        return singles;
    }

    public List<Single> getAllDoneSingles(){
        var singles = this.singleRepository.getAllDoneSingle();
        return singles;
    }

    public Single getSingleById(Integer id){
        var checkSingle = this.singleRepository.findById(id);
        if (!checkSingle.isPresent()){
            throw new RuntimeException("Id not found");
        }
        var realSingle = checkSingle.get();
        return realSingle;
    }

    public Single createNewSingle(CreateSingleResponse singleResponse){
        Optional<Picture> defaultPicture =  this.pictureRepository.findById(42);
        Optional<Category> defaultCategory = this.categoryRepository.findById(4);
        Single newSingle = new Single();
        newSingle.setTitle(singleResponse.getTitle());
        newSingle.setDescription(singleResponse.getDescription());
        newSingle.setPublisher(singleResponse.getPublisher());
        newSingle.setGenre(singleResponse.getGenre());
        newSingle.setReleaseYear(singleResponse.getReleaseYear());
        newSingle.setLength(singleResponse.getLength());
        newSingle.setPicture(defaultPicture.get());
        newSingle.setCategory(defaultCategory.get());
        newSingle.setIsValid(false);
        newSingle.setStatus(false);
        this.singleRepository.save(newSingle);
        return newSingle;
    }

    public Single updateSingleById(Integer id,CreateSingleResponse singleResponse ){
        var checkedSingle = this.singleRepository.findById(id);
        var updateCategory = this.categoryRepository.findById(singleResponse.getCategoryId());
        if (!checkedSingle.isPresent()){
            throw  new RuntimeException("Id not found");
        }
        var realSingle = checkedSingle.get();
        realSingle.setTitle(singleResponse.getTitle());
        realSingle.setDescription(singleResponse.getDescription());
        realSingle.setPublisher(singleResponse.getPublisher());
        realSingle.setGenre(singleResponse.getGenre());
        realSingle.setReleaseYear(singleResponse.getReleaseYear());
        realSingle.setLength(singleResponse.getLength());
        realSingle.setCategory(updateCategory.get());
        realSingle.setIsValid(true);
        this.singleRepository.save(realSingle);
        return realSingle;
    }
    public Single updatePictureForSingleById(Integer id, MultipartFile file) throws IOException{
        var checkSingle = this.singleRepository.findById(id);
        if (!checkSingle.isPresent()){
            throw new RuntimeException("Id not found");
        }
        var realSingle = checkSingle.get();

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
        realSingle.setPicture(savedPicture);
        realSingle.setIsValid(true);
        this.singleRepository.save(realSingle);
        return realSingle;
    }

    public Single activateSingleById(Integer id){
        var checkSingle = this.singleRepository.findById(id);
        if (!checkSingle.isPresent()){
            throw new RuntimeException("Id not found");
        }
        var realSingle = checkSingle.get();
        realSingle.setStatus(true);
        realSingle.setIsValid(true);
        this.singleRepository.save(realSingle);
        return realSingle;
    }
}
