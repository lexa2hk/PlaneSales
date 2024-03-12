package com.example.planesales.DTO;

import com.example.planesales.Entity.CompanyMark;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link CompanyMark}
 */
@Value
@Builder
public class CompanyMarkDto implements Serializable {
    String mark;
    String markText;
    String companyName;

}