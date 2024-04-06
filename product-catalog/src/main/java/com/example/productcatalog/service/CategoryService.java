package com.example.productcatalog.service;

import com.example.productcatalog.entity.Category;
import com.example.productcatalog.repository.CategoryRepository;
import com.example.productcatalog.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public ResponseData<Category> createCategory(Category category) {
        ResponseData<Category> response = new ResponseData<Category>();
        Category cat = categoryRepository.save(category);
        if (cat != null) {
            response.setData(cat);
            response.setMessage("Category Created");
            response.setStatus(true);
        } else {
            response.setStatus(false);
            response.setMessage("Category not Created");
        }
        return response;
    }

    public String updateCategory(Integer categoryId, Category category) {
        Category existingCategory = categoryRepository.findById(categoryId).orElse(null);
        if (existingCategory == null) {
            return "Category not found!";
        }

        existingCategory.setName(category.getName());
        Date updatedDate = new Date();
        existingCategory.setUpdated_at(updatedDate);
        categoryRepository.save(existingCategory);
        return "Category Updated Successfully!";
    }

    public ResponseData<String> destroyCategory(Integer categoryId) {
        ResponseData<String> response = new ResponseData<String>();
        categoryRepository.deleteById(categoryId);
        response.setStatus(true);
        response.setMessage("Category deleted successfully!");
        return response;
    }

    public ResponseData<List<Category>> getAllCategories() {
        ResponseData<List<Category>> response = new ResponseData<List<Category>>();
        List<Category> categories = categoryRepository.findAll();

        if (!categories.isEmpty()) {
            response.setData(categories);
            response.setStatus(true);
            response.setMessage("have Categories");
        } else {
            response.setStatus(false);
            response.setMessage("No Category found!");
        }
        return response;
    }
}
