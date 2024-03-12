package com.example.supplymanagement.controller;

import com.example.supplymanagement.entity.Supplier;
import com.example.supplymanagement.service.ResponseData;
import com.example.supplymanagement.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @PostMapping(path = "/api/v1/suppliers")
    public ResponseData<Supplier> createSupplier(@RequestBody Supplier supplier){
        return supplierService.createSupplier(supplier);
    }
}
