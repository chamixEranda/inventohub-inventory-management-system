package com.example.productcatalog.repository;

import com.example.productcatalog.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findByCode(String code);

    @Query("select p from Product p where p.name=?1")
    List<Product> findProductByName(String name);
}
