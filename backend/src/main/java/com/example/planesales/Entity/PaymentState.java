package com.example.planesales.Entity;

import lombok.Getter;

@Getter
public enum PaymentState {
    PENDING("Pending"),
    NOT_PAID("Not Paid"),
    PARTIALLY_PAID("Partially Paid"),
    PAID("Paid"),
    REFUNDED("Refunded");

    private final String displayValue;

    PaymentState(String displayValue) {
        this.displayValue = displayValue;
    }


}