package com.example.planesales.DTO;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.planesales.Entity.Company}
 */
@Value
@Builder
public class CompanyDto implements Serializable {
    String mainInfo;
    String park;
    String since;
    Integer annualPassTraffic;
}