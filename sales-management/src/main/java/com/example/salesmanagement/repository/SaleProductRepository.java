package com.example.salesmanagement.repository;

import com.example.salesmanagement.entity.SaleProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleProductRepository extends JpaRepository<SaleProduct, Integer> {

    @Query("select s from SaleProduct s where s.sale_id=?1")
    List<SaleProduct> getSaleListBySaleId(int sale_id);
}
