package com.example.salesmanagement.repository;

import com.example.salesmanagement.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Customer findByEmail(String email);

    @Query("select c from Customer c where c.f_name=?1")
    List<Customer> findProductByName(String f_name);
}
