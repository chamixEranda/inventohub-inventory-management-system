package com.example.salesmanagement.repository;

import com.example.salesmanagement.entity.SaleProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleProductRepository extends JpaRepository<SaleProduct, Integer> {
}
