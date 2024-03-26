package com.example.salesmanagement.entity;

import jakarta.persistence.*;

import java.text.DecimalFormat;
import java.util.Date;

@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "reference_no")
    private String reference_no;

    @Column(name = "customer_id")
    private int customer_id;

    @Column(name = "total_amount")
    private Double total_amount;

    @Column(name = "order_discount")
    private Double order_discount;

    @Column(name = "grand_total")
    private Double grand_total;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date created_at;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updated_at;

    public Sale() {
    }

    public Sale(int id, String reference_no, int customer_id, Double total_amount, Double order_discount, Double grand_total, Date created_at, Date updated_at) {
        this.id = id;
        this.reference_no = reference_no;
        this.customer_id = customer_id;
        this.total_amount = total_amount;
        this.order_discount = order_discount;
        this.grand_total = grand_total;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReference_no() {
        return reference_no;
    }

    public void setReference_no(String reference_no) {
        this.reference_no = reference_no;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public Double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(Double total_amount) {
        this.total_amount = total_amount;
    }

    public Double getOrder_discount() {
        return order_discount;
    }

    public void setOrder_discount(Double order_discount) {
        this.order_discount = order_discount;
    }

    public Double getGrand_total() {
        return grand_total;
    }

    public void setGrand_total(Double grand_total) {
        this.grand_total = grand_total;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
}
