package com.example.salesmanagement.dto;

public class SaleRequest {
    private int customer_id;
    private Double total_amount;
    private Double order_discount;
    private Double grand_total;
    private Double paid_amount;
    private int[] product_id;
    private int[] qty;
    private Double[] unit_prices;
    private Double[] total;

    public SaleRequest() {
    }

    public SaleRequest(int customer_id, Double total_amount, Double order_discount, Double grand_total, Double paid_amount, int[] product_id, int[] qty, Double[] unit_prices, Double[] total) {
        this.customer_id = customer_id;
        this.total_amount = total_amount;
        this.order_discount = order_discount;
        this.grand_total = grand_total;
        this.paid_amount = paid_amount;
        this.product_id = product_id;
        this.qty = qty;
        this.unit_prices = unit_prices;
        this.total = total;
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

    public Double getPaid_amount() {
        return paid_amount;
    }

    public void setPaid_amount(Double paid_amount) {
        this.paid_amount = paid_amount;
    }

    public int[] getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int[] product_id) {
        this.product_id = product_id;
    }

    public int[] getQty() {
        return qty;
    }

    public void setQty(int[] qty) {
        this.qty = qty;
    }

    public Double[] getUnit_prices() {
        return unit_prices;
    }

    public void setUnit_prices(Double[] unit_prices) {
        this.unit_prices = unit_prices;
    }

    public Double[] getTotal() {
        return total;
    }

    public void setTotal(Double[] total) {
        this.total = total;
    }
}

