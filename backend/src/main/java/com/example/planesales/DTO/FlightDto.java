package com.example.planesales.DTO;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.example.planesales.Entity.Flight}
 */
@Value
@Builder
public class FlightDto implements Serializable {
    String route;
    int passengerQty;
    Integer duration;
    List<CompanyDto> companies;
}