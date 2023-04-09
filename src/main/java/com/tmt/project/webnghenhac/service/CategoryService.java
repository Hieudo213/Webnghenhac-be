package com.tmt.project.webnghenhac.service;

import com.tmt.project.webnghenhac.domain.Category;
import com.tmt.project.webnghenhac.repository.CategoryRepository;
import com.tmt.project.webnghenhac.repository.PictureRepository;
import com.tmt.project.webnghenhac.service.request.CreateCategoryRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final PictureRepository pictureRepository;

    public CategoryService(CategoryRepository categoryRepository, PictureRepository pictureRepository) {
        this.categoryRepository = categoryRepository;
        this.pictureRepository = pictureRepository;
    }

    public List<Category> getAllCategory(){
        List<Category> categories = this.categoryRepository.findGenre();
        return categories;
    }

    public Category getCategoryById(Integer id){
        Optional<Category> chekedCategory = this.categoryRepository.findById(id);
        if (!chekedCategory.isPresent()){
            throw new RuntimeException("Id Not Found");
        }
        Category realCategory = chekedCategory.get();
        return realCategory;
    }

    public Category createNewCategory(CreateCategoryRequest category){
        var defaultPicture = this.pictureRepository.findById(42);
        Category newCategory = new Category();
        newCategory.setName(category.getName());
        newCategory.setDescription(category.getDescription());
        newCategory.setPicture(defaultPicture.get());
        this.categoryRepository.save(newCategory);
        return newCategory;
    }

    public Category updateCategoryById(Category category, Integer id){
        var checkedCategory = this.categoryRepository.findById(id);
        if (!checkedCategory.isPresent()){
            throw new RuntimeException("Id not found");
        }
        var realCategory = checkedCategory.get();
        realCategory.setName(category.getName());
        realCategory.setDescription(category.getDescription());
        realCategory.setIsValid(true);
        this.categoryRepository.save(realCategory);
        return realCategory;
    }

}
