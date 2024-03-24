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
    public ResponseData<Product> createProduct(@RequestParam("name") String name,
                                               @RequestParam("code") String code,
                                               @RequestParam("category_id") Integer categoryId,
                                               @RequestParam("price") BigDecimal price,
                                               @RequestParam("cost") BigDecimal cost,
                                               @RequestParam("qty") Integer qty,
                                               @RequestParam("alert_quantity") Integer alertQuantity,
                                               @RequestParam("image") MultipartFile image,
                                               @RequestParam("description") String description) {

        Product product = new Product();
        product.setName(name);
        product.setCode(code);
        product.setCategory_id(categoryId);
        product.setPrice(price);
        product.setCost(cost);
        product.setQty(qty);
        product.setAlert_quantity(alertQuantity);
        product.setDescription(description);

        return productService.createProduct(product, image);
    }


    @PutMapping(path = "api/v1/products/{product_id}")
    public ResponseData<Product> updateProduct(@PathVariable Integer product_id, @RequestBody Product product) {
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
