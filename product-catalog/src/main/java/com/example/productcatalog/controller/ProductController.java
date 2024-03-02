package com.example.productcatalog.controller;

import com.example.productcatalog.entity.Product;
import com.example.productcatalog.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(path = "/api/v1/products")
    public String createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }

    @GetMapping(path = "/api/v1/products")
    public List<Product> getAllProduct(){
        return productService.getAllProducts();
    }
}
