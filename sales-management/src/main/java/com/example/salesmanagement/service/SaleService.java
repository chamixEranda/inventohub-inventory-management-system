package com.example.salesmanagement.service;

import com.example.salesmanagement.entity.Sale;
import com.example.salesmanagement.entity.SaleProduct;
import com.example.salesmanagement.repository.SaleProductRepository;
import com.example.salesmanagement.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private SaleProductRepository saleProductRepository;

    public ResponseData<Sale> createSale(SaleRequest saleRequest){
        ResponseData<Sale> responseData = new ResponseData<Sale>();

        Sale newSale = new Sale();
        newSale.setReference_no("INV-" + new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date()));
        newSale.setCustomer_id(saleRequest.getCustomer_id());
        newSale.setTotal_amount(saleRequest.getTotal_amount());
        newSale.setOrder_discount(saleRequest.getOrder_discount());
        newSale.setGrand_total(saleRequest.getGrand_total());
        newSale.setCreated_at(new Date());
        newSale.setUpdated_at(new Date());

        saleRepository.save(newSale);

        for (int i = 0; i < saleRequest.getProduct_id().length; i++) {
            SaleProduct saleProduct = new SaleProduct();
            saleProduct.setSale_id(newSale.getId());
            saleProduct.setProduct_id(saleRequest.getProduct_id()[i]);
            saleProduct.setQty(saleRequest.getQty()[i]);
            saleProduct.setUnit_price(saleRequest.getUnit_prices()[i]);
            saleProduct.setTotal(saleRequest.getTotal()[i]);

            saleProductRepository.save(saleProduct);
        }

        responseData.setMessage("Sale Created successfully!");
        responseData.setStatus(true);
        responseData.setData(newSale);

        return responseData;
    }

    public ResponseData<List<Sale>> getAllSales(){
        ResponseData<List<Sale>> responseData = new ResponseData<List<Sale>>();
        List<Sale> saleList = saleRepository.findAll();
        if (!saleList.isEmpty()){
            responseData.setStatus(true);
            responseData.setMessage("Sale List found!");
            responseData.setData(saleList);
        } else {
            responseData.setStatus(false);
            responseData.setMessage("Sales Empty!");
        }

        return responseData;
    }

    public ResponseData<Sale> deleteSale(int id){
        ResponseData<Sale> responseData = new ResponseData<Sale>();
        Optional<Sale> sale = saleRepository.findById(id);
        if (!sale.isEmpty()){
            List<SaleProduct> saleProducts = saleProductRepository.getSaleListBySaleId(id);
            for (SaleProduct saleProduct : saleProducts) {
                saleProductRepository.delete(saleProduct);
            }
            saleRepository.deleteById(id);
            responseData.setStatus(true);
            responseData.setMessage("Sale deleted successfully!");
        }

        return responseData;
    }
}
