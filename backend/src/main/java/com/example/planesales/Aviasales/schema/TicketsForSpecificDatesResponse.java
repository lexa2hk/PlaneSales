package com.example.planesales.Aviasales.schema;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TicketsForSpecificDatesResponse {
    @JsonProperty("data")
    private List<TicketsForSpecificDatesData> data;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("success")
    private boolean success;

}