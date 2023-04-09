package com.tmt.project.webnghenhac.service;

import com.tmt.project.webnghenhac.domain.Browse;
import com.tmt.project.webnghenhac.repository.BrowseRepository;
import com.tmt.project.webnghenhac.repository.CategoryRepository;
import com.tmt.project.webnghenhac.repository.PictureRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrowseService {
    private final BrowseRepository browseRepository;
    private final PictureRepository pictureRepository;
    private final CategoryRepository categoryRepository;

    public BrowseService(BrowseRepository browseRepository, PictureRepository pictureRepository, CategoryRepository categoryRepository) {
        this.browseRepository = browseRepository;
        this.pictureRepository = pictureRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Browse> getAllBrowse(){
        List<Browse> browses = this.browseRepository.findAll();
        return browses;
    }

    public Browse getBrowseById(Integer id){
        Optional<Browse> checkedBrowse = this.browseRepository.findById(id);
        if (!checkedBrowse.isPresent()){
            throw new RuntimeException("Id not found!");
        }
        Browse realBrowse = checkedBrowse.get();
        return realBrowse;
    }

    public Browse createNewBrowse(Browse browse){
        var defaultPicture = this.pictureRepository.findById(42);
        var defaultCategory = this.categoryRepository.findById(0);
        Browse newBrowse = new Browse();
        newBrowse.setTitle(browse.getTitle());
        newBrowse.setDescription(browse.getDescription());
        newBrowse.setPicture(defaultPicture.get());
        newBrowse.setCategory(defaultCategory.get());
        this.browseRepository.save(newBrowse);
        return newBrowse;
    }
}
