package com.example.planesales.Entity;

public enum TechnicalState {
    OPERATIONAL("Operational"),
    MAINTENANCE("Maintenance"),
    RETIRED("Retired");

    private final String displayValue;

    TechnicalState(String displayValue) {
        this.displayValue = displayValue;
    }
}
