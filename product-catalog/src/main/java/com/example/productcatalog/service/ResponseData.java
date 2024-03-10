package com.example.productcatalog.service;

public class ResponseData<Product> {

    private boolean status;
    private String message;
    private Product data;

    public ResponseData() {
    }

    public ResponseData(boolean status, String message, Product data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Product getData() {
        return data;
    }

    public void setData(Product data) {
        this.data = data;
    }
}
