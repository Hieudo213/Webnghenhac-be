package com.tmt.project.webnghenhac.controller;

import com.tmt.project.webnghenhac.domain.Category;
import com.tmt.project.webnghenhac.service.CategoryService;
import com.tmt.project.webnghenhac.service.request.CreateCategoryRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/public/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public ResponseEntity<List<Category>> getAllCategory(){
        List<Category> categories = this.categoryService.getAllCategory();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") Integer id){
        Category category = this.categoryService.getCategoryById(id);
        return ResponseEntity.ok(category);
    }

    @PostMapping("/create")
    public ResponseEntity<Category> createNewCategory(@RequestBody CreateCategoryRequest category){
        var newCategory = this.categoryService.createNewCategory(category);
        return ResponseEntity.ok(newCategory);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category, @PathVariable("id") Integer id){
        var updateCategory = this.categoryService.updateCategoryById(category, id);
        return ResponseEntity.ok(updateCategory);
    }

}
