package com.example.planesales.DTO;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.planesales.Entity.Place}
 */
@Value
@Builder
public class PlaceDto implements Serializable {
    String row_name;
    String classType;
    PlaneDto plane;
}