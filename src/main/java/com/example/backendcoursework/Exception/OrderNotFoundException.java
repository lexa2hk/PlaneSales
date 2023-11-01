package com.example.backendcoursework.Exception;

public class OrderNotFoundException extends Exception {
    public OrderNotFoundException(String message) {
        super("Order not found for data: " + message);
    }

}
