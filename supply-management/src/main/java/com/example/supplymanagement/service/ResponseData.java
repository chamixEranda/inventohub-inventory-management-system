package com.example.supplymanagement.service;

public class ResponseData<Supplier> {

    private boolean status;
    private String message;
    private Supplier data;

    public ResponseData() {
    }

    public ResponseData(boolean status, String message, Supplier data) {
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

    public Supplier getData() {
        return data;
    }

    public void setData(Supplier data) {
        this.data = data;
    }
}
