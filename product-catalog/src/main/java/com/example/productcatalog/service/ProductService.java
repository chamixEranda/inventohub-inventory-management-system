package com.example.productcatalog.service;

import com.example.productcatalog.entity.Product;
import com.example.productcatalog.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public String createProduct(Product product){
        Product existingProduct = productRepository.findByCode(product.getCode());
        if (existingProduct != null){
            return "Product code should be unique";
        }
        productRepository.save(product);
        return "Product created sucessfully!";
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
}
