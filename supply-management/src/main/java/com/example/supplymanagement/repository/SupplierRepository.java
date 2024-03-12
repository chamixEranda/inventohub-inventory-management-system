package com.example.supplymanagement.repository;

import com.example.supplymanagement.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

    Supplier findByEmail(String email);
}
