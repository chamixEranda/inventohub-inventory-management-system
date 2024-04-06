package com.example.productcatalog.controller;

import com.example.productcatalog.entity.Category;
import com.example.productcatalog.service.CategoryService;
import com.example.productcatalog.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping(path = "/api/v1/categories")
    public ResponseData<Category> createCategory(@RequestBody Category category){
        return categoryService.createCategory(category);
    }

    @PutMapping(path = "/api/v1/categories/{categoryId}")
    public String updateCategory(@PathVariable Integer categoryId, @RequestBody Category category){
        return categoryService.updateCategory(categoryId, category);
    }

    @DeleteMapping(path = "/api/v1/categories/{id}")
    public ResponseData<String> destroyCategory(@PathVariable Integer id){
        System.out.println("Done Deteled");
        return categoryService.destroyCategory(id);
    }

    @GetMapping(path = "/api/v1/categories")
    public ResponseData<List<Category>> getAllCategories(){
        return categoryService.getAllCategories();
    }
}
