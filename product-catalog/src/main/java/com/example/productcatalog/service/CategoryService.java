package com.example.productcatalog.service;

import com.example.productcatalog.entity.Category;
import com.example.productcatalog.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category createCategory(Category category){
        return categoryRepository.save(category);
    }

    public String updateCategory(Integer categoryId, Category category){
        Category existingCategory = categoryRepository.findById(categoryId).orElse(null);
        if (existingCategory == null){
            return "Category not found!";
        }

        existingCategory.setName(category.getName());
        Date updatedDate = new Date();
        existingCategory.setUpdated_at(updatedDate);
        categoryRepository.save(existingCategory);
        return "Category Updated Successfully!";
    }

    public String destroyCategory(Integer categoryId){
        Category category = categoryRepository.findById(categoryId).orElse(null);
        if (category == null){
            return "Category not found!";
        }

        category.setIs_active(false);
        categoryRepository.save(category);
        return "Category deleted successfully!";
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }
}
