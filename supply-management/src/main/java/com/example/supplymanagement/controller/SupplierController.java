package com.example.supplymanagement.controller;

import com.example.supplymanagement.entity.Supplier;
import com.example.supplymanagement.service.ResponseData;
import com.example.supplymanagement.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @PostMapping(path = "/api/v1/suppliers")
    public ResponseData<Supplier> createSupplier(@RequestBody Supplier supplier){
        return supplierService.createSupplier(supplier);
    }

    @PutMapping(path = "/api/v1/suppliers/{id}")
    public ResponseData<Supplier> updateSupplier(@PathVariable int id,@RequestBody Supplier supplier){
        return supplierService.updateSupplier(id,supplier);
    }

    @DeleteMapping(path = "/api/v1/suppliers/{id}")
    public ResponseData<Supplier> deleteSupplier(@PathVariable int id){
        return supplierService.deleteSupplier(id);
    }

    @GetMapping(path = "/api/v1/suppliers")
    public List<Supplier> getAllActiveSuppliers(){
        return supplierService.getAllActiveSuppliers();
    }
}
