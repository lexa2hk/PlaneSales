package com.example.planesales.Exception;

public class OrderNotFoundException extends Exception {
    public OrderNotFoundException(String message) {
        super("Order not found for data: " + message);
    }

}
