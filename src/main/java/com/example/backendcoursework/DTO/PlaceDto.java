package com.example.backendcoursework.DTO;

import lombok.Builder;
import lombok.Value;
import org.springframework.context.annotation.Bean;

import java.io.Serializable;

/**
 * DTO for {@link com.example.backendcoursework.Entity.Place}
 */
@Value
@Builder
public class PlaceDto implements Serializable {
    String row_name;
    String classType;
    PlaneDto plane;
}