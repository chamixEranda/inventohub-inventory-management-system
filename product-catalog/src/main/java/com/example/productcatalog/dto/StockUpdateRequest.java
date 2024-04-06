package com.example.productcatalog.dto;

public class StockUpdateRequest {
    private int[] product_id;
    private int[] quantities;

    public StockUpdateRequest() {
    }

    public StockUpdateRequest(int[] product_id, int[] quantities) {
        this.product_id = product_id;
        this.quantities = quantities;
    }

    public int[] getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int[] product_id) {
        this.product_id = product_id;
    }

    public int[] getQuantities() {
        return quantities;
    }

    public void setQuantities(int[] quantities) {
        this.quantities = quantities;
    }
}

