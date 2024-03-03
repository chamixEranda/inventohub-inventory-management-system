package com.example.productcatalog.service;

import com.example.productcatalog.entity.Product;
import com.example.productcatalog.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    private static final String UPLOAD_DIR = "uploads/";

    public ResponseEntity<String> createProduct(Product product, MultipartFile imageFile){
        Product existingProduct = productRepository.findByCode(product.getCode());
        if (existingProduct != null){
            String message = "Product code should be unique";
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }

        String imageName = StringUtils.cleanPath(imageFile.getOriginalFilename());
        try{
            Path imagePath = Paths.get(UPLOAD_DIR + imageName);
            Files.copy(imageFile.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to upload image.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        product.setImage(imageName);
        productRepository.save(product);
        String message = "Product created sucessfully!";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    public ResponseEntity<String> updateProduct(int id, Product product){
        Product updateProduct = productRepository.findById(id).orElse(null);
        if (updateProduct != null){
            updateProduct.setName(product.getName());
            updateProduct.setCode(product.getCode());
            updateProduct.setCategory_id(product.getCategory_id());
            updateProduct.setPrice(product.getPrice());
            updateProduct.setCost(product.getCost());
            updateProduct.setAlert_quantity(product.getAlert_quantity());
            updateProduct.setImage(product.getImage());
            updateProduct.setDescription(product.getDescription());
            updateProduct.setUpdated_at(new Date());
            productRepository.save(updateProduct);

            String message = "Product updated successfully!";
            return new ResponseEntity<>(message, HttpStatus.OK);
        }

        String message = "Product Not Found!";
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> deleteProduct(int id){
        Product product =  productRepository.findById(id).orElse(null);
        if (product != null){
            product.setIs_active(false);
            productRepository.save(product);

            String message = "Product deleted sucessfully!";
            return new ResponseEntity<>(message, HttpStatus.OK);
        }

        String message = "Product Not Fond!";
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public List<Product> findProductByName(String name){
        return productRepository.findProductByName(name);
    }

    public Optional<Product> findProductById(int id){
        return productRepository.findById(id);
    }
}
