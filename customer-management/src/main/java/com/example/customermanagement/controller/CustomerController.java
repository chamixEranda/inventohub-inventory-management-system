package com.example.customermanagement.controller;

import com.example.customermanagement.entity.Customer;
import com.example.customermanagement.service.CustomerService;
import com.example.customermanagement.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping(path = "/api/v1/customers")
    public ResponseData<Customer> createCustomer(@RequestBody Customer customer){
        return customerService.createCustomer(customer);
    }

    @GetMapping(path = "/api/v1/customers/{id}")
    public ResponseData<Customer> getCustomerById(@PathVariable int id){
        return customerService.getCustomerById(id);
    }

    @PutMapping(path = "/api/v1/customers/{id}")
    public ResponseData<Customer> updateCustomer(@PathVariable int id, @RequestBody Customer customer){
        return customerService.updateCustomer(id, customer);
    }

    @DeleteMapping(path = "/api/v1/customers/{id}")
    public ResponseData<Customer> deleteCustomer(@PathVariable int id){
        return customerService.deleteCustomer(id);
    }

    @GetMapping(path = "/api/v1/customers")
    public ResponseData<List<Customer>> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping(path = "/api/v1/customers", params = "f_name")
    public ResponseData<List<Customer>> findProductByName(@RequestParam String f_name){
        return customerService.getCustomerByName(f_name);
    }
}
