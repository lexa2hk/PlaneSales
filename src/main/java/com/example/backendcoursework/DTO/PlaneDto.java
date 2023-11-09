package com.example.backendcoursework.DTO;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.backendcoursework.Entity.Plane}
 */
@Value
@Builder
public class PlaneDto implements Serializable {
    String model;
    String calSign;
    int capacity;
    FlightDto flight;
}