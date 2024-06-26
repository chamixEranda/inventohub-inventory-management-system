package com.example.productcatalog.controller;

import com.example.productcatalog.entity.Product;
import com.example.productcatalog.service.ProductService;
import com.example.productcatalog.service.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(path = "/api/v1/products")
    public ResponseData<Product> createProduct(@RequestBody Product product) {

        return productService.createProduct(product);
    }


    @PutMapping(path = "api/v1/products/{product_id}")
    public ResponseData<Product> updateProduct(@PathVariable Integer product_id, @RequestBody Product product) {
        System.out.println(product.getQty());
        return productService.updateProduct(product_id, product);
    }

    @GetMapping(path = "/api/v1/products")
    public ResponseData<List<Product>> getAllProduct() {
        return productService.getAllProducts();
    }

    @GetMapping(path = "/api/v1/products", params = "name")
    public List<Product> findProductByName(@RequestParam String name) {
        return productService.findProductByName(name);
    }

    @GetMapping("/api/v1/products/{id}") // Corrected
    public ResponseData<Product> findProductById(@PathVariable int id) {
        System.out.println("Calling this method"); // Updated log message
        return productService.findProductById(id);
    }

    @DeleteMapping(path = "/api/v1/products/{id}")
    public ResponseData<Product> deleteProduct(@PathVariable int id) {
        return productService.deleteProduct(id);
    }
}
