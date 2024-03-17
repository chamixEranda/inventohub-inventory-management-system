package com.example.supplymanagement.service;

import com.example.supplymanagement.entity.Supplier;
import com.example.supplymanagement.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public ResponseData<Supplier> updateSupplier(int id, Supplier supplier){
        ResponseData<Supplier> respose = new ResponseData<Supplier>();
        Supplier updateSupplier = supplierRepository.findById(id).orElse(null);
        if (updateSupplier != null){
            updateSupplier.setFirst_name(supplier.getFirst_name());
            updateSupplier.setLast_name(supplier.getLast_name());
            updateSupplier.setCompany_name(supplier.getCompany_name());
            updateSupplier.setEmail(supplier.getEmail());
            updateSupplier.setPhone_number(supplier.getPhone_number());
            updateSupplier.setAddress(supplier.getAddress());
            supplierRepository.save(updateSupplier);

            respose.setMessage("Supplier updated successfully!");
            respose.setStatus(true);
            respose.setData(updateSupplier);
        } else {
            respose.setMessage("Supplier not found!");
            respose.setStatus(false);
        }

        return respose;
    }

    public ResponseData<Supplier> deleteSupplier(int id){
        ResponseData<Supplier> response = new ResponseData<Supplier>();
        Supplier supplier = supplierRepository.findById(id).orElse(null);
        supplierRepository.delete(supplier);

        response.setStatus(true);
        response.setMessage("Supplier deleted successfully!");

        return response;
    }

    public List<Supplier> getAllActiveSuppliers(){
        return supplierRepository.findAll();
    }

}
