package com.example.planesales.Aviasales.schema;

import lombok.Builder;
import lombok.Setter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Setter
@Builder
public class TicketsForSpecificDatesParams {
    private String origin;

    private String destination;

    private int limit;

    private boolean direct;

    private String departureAt;

    private String returnAt;

    private boolean oneWay;

    public MultiValueMap<String, String> toQueryParams() {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();

        if (origin != null) {
            queryParams.put("origin", Collections.singletonList(origin));
        }

        if (destination != null) {
            queryParams.put("destination", Collections.singletonList(destination));
        }

        if (limit > 0) {
            queryParams.put("limit", Collections.singletonList(String.valueOf(limit)));
        }

        queryParams.put("direct", Collections.singletonList(String.valueOf(direct)));

        if (departureAt != null) {
            queryParams.put("departure_at", Collections.singletonList(departureAt));
        }

        if (returnAt != null) {
            queryParams.put("return_at", Collections.singletonList(returnAt));
        }

        queryParams.put("one_way", Collections.singletonList(String.valueOf(oneWay)));

        return queryParams;
    }
}