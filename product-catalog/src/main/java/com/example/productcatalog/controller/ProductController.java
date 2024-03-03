package com.example.productcatalog.controller;

import com.example.productcatalog.entity.Product;
import com.example.productcatalog.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(path = "/api/v1/products")
    public ResponseEntity<String> createProduct(@RequestBody Product product, @RequestParam("imageFile") MultipartFile imageFile){
        return productService.createProduct(product, imageFile);
    }

    @PutMapping(path = "api/v1/products/{product_id}")
    public ResponseEntity<String> updateProduct(@PathVariable Integer product_id, @RequestBody Product product){
        return productService.updateProduct(product_id, product);
    }

    @GetMapping(path = "/api/v1/products")
    public List<Product> getAllProduct(){
        return productService.getAllProducts();
    }

    @GetMapping(path = "/api/v1/products", params = "name")
    public List<Product> findProductByName(@RequestParam String name){
        return productService.findProductByName(name);
    }

    @RequestMapping(name = "/api/v1/products", method = RequestMethod.POST)
    public Optional<Product> findProductById(@RequestBody int id){
        return productService.findProductById(id);
    }

    @DeleteMapping(path = "/api/v1/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        return productService.deleteProduct(id);
    }
}
