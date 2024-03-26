package com.example.salesmanagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "product_sales")
public class SaleProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "sale_id")
    private int sale_id;

    @Column(name = "product_id")
    private int product_id;

    @Column(name = "qty")
    private int qty;

    @Column(name = "unit_price")
    private Double unit_price;

    @Column(name = "total")
    private Double total;

    public SaleProduct() {
    }

    public SaleProduct(int id, int sale_id, int product_id, int qty, Double unit_price, Double total) {
        this.id = id;
        this.sale_id = sale_id;
        this.product_id = product_id;
        this.qty = qty;
        this.unit_price = unit_price;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSale_id() {
        return sale_id;
    }

    public void setSale_id(int sale_id) {
        this.sale_id = sale_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(Double unit_price) {
        this.unit_price = unit_price;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
