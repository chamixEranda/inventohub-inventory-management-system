package com.example.supplymanagement.service;

import com.example.supplymanagement.entity.Supplier;
import com.example.supplymanagement.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public ResponseData<Supplier> createSupplier(Supplier supplier){
        ResponseData<Supplier> response = new ResponseData<Supplier>();
        Supplier existsEmail = supplierRepository.findByEmail(supplier.getEmail());
        if (existsEmail != null){
            response.setStatus(false);
            response.setMessage("Email already exists!");
        } else {
            supplierRepository.save(supplier);
            response.setStatus(true);
            response.setMessage("Supplier created successfully!");
            response.setData(supplier);
        }

        return response;
    }

}
