package com.example.productcatalog.controller;

import com.example.productcatalog.entity.Category;
import com.example.productcatalog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping(path = "/api/v1/categories")
    public Category createCategory(@RequestBody Category category){
        return categoryService.createCategory(category);
    }

    @PutMapping(path = "/api/v1/categories/{categoryId}")
    public String updateCategory(@PathVariable Integer categoryId, @RequestBody Category category){
        return categoryService.updateCategory(categoryId, category);
    }

    @DeleteMapping(path = "/api/v1/categories/{categoryId}")
    public String destroyCategory(@PathVariable Integer categoryId){
        return categoryService.destroyCategory(categoryId);
    }
}
