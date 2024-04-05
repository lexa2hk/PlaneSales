package com.example.planesales.Entity;

public enum ReviewObjective {
    PLANE("PLANE"),
    FLIGHT("FLIGHT"),
    AIRPORT("AIRPORT"),
    COMPANY("COMPANY");

    private String objective;

    ReviewObjective(String objective) {
        this.objective = objective;
    }

    public String getObjective() {
        return objective;
    }
}
