package com.example.salesmanagement.controller;

import com.example.salesmanagement.entity.Sale;
import com.example.salesmanagement.dto.ResponseData;
import com.example.salesmanagement.dto.SaleRequest;
import com.example.salesmanagement.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @PostMapping(path = "/api/v1/sales")
    public ResponseData<Sale> createSale(@RequestBody SaleRequest sale){
        return saleService.createSale(sale);
    }

    @GetMapping(path = "/api/v1/sales")
    public ResponseData<List<Sale>> getAllSales(){
        return saleService.getAllSales();
    }

    @DeleteMapping(path = "/api/v1/sales/{id}")
    public ResponseData<Sale> deleteSale(@PathVariable int id){
        return saleService.deleteSale(id);
    }
}
