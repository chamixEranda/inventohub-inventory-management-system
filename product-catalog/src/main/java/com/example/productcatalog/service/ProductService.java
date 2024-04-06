package com.example.productcatalog.service;

import com.example.productcatalog.entity.Product;
import com.example.productcatalog.repository.ProductRepository;
import com.example.productcatalog.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    private static final String UPLOAD_DIR = "uploads/";

    public ResponseData<Product> createProduct(Product product) {
        ResponseData<Product> response = new ResponseData<Product>();

       try {
           Product existingProduct = productRepository.findByCode(product.getCode());
           if (existingProduct != null) {
               response.setStatus(false);
               response.setMessage("Product code should be unique");
           }
           productRepository.save(product);
           response.setStatus(true);
           response.setMessage("Product created sucessfully!");
           response.setData(product);

           return response;
       }catch (Exception e){
           response.setStatus(false);
           response.setMessage(e.getMessage());
           return response;
       }
    }
    public static String convertMultipartFileToBase64(MultipartFile file) throws IOException {
        byte[] fileContent = file.getBytes();
        return Base64.getEncoder().encodeToString(fileContent);
    }

    public ResponseData<Product> updateProduct(int id, Product product) {
        ResponseData<Product> response = new ResponseData<Product>();
        Product updateProduct = productRepository.findById(id).orElse(null);
        if (updateProduct != null) {
            updateProduct.setName(product.getName());
            updateProduct.setCode(product.getCode());
            updateProduct.setCategory_id(product.getCategory_id());
            updateProduct.setPrice(product.getPrice());
            updateProduct.setQty(product.getQty());
            updateProduct.setCost(product.getCost());
            updateProduct.setAlert_quantity(product.getAlert_quantity());
            updateProduct.setImage(product.getImage());
            updateProduct.setDescription(product.getDescription());
            updateProduct.setUpdated_at(new Date());
            productRepository.save(updateProduct);

            response.setStatus(true);
            response.setMessage("Product updated successfully!");
            response.setData(updateProduct);
        } else {
            response.setStatus(false);
            response.setMessage("Product Not Found!");
        }

        return response;
    }

    public ResponseData<Product> deleteProduct(int id) {
        ResponseData<Product> response = new ResponseData<Product>();
         productRepository.deleteById(id);
        response.setStatus(true);
        response.setMessage("Product deleted");
        return response;
    }

    public ResponseData<List<Product>> getAllProducts() {
        ResponseData<List<Product>> response = new ResponseData<List<Product>>();
        List<Product> products = productRepository.findAll();

        if (!products.isEmpty()) {
            response.setStatus(true);
            response.setMessage("products found!");
            response.setData(products);
        } else {
            response.setStatus(false);
            response.setMessage("products Empty!");
        }

        return response;
    }

    public List<Product> findProductByName(String name) {
        return productRepository.findProductByName(name);
    }

    public ResponseData<Product> findProductById(int id) {
        ResponseData<Product> response = new ResponseData<Product>();
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            response.setMessage("Product Found!");
            response.setStatus(true);
            response.setData(product.get());

        } else {
            response.setMessage("Product Not found!");
            response.setStatus(false);
        }
        return response;
    }

    public ResponseData<String> updateProductStock(int[] productIds, int[] quantities){
        ResponseData<String> responseData = new ResponseData<String>();
        for (int i = 0; i < productIds.length; i++) {
            Product product = productRepository.findById(productIds[i]).orElse(null);
            if (product != null){
                product.setQty(product.getQty() - quantities[i]);
                productRepository.save(product);

                responseData.setMessage("Product Stock Updated!");
                responseData.setStatus(true);
            } else {
                responseData.setMessage("Product Not Found!");
                responseData.setStatus(false);
            }
        }
        return responseData;
    }
}
