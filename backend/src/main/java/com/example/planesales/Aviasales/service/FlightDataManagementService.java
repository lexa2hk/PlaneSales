package com.example.planesales.Aviasales.service;

import com.example.planesales.Aviasales.db.repository.FlightsForDatesRepository;
import com.example.planesales.Aviasales.schema.TicketsForSpecificDatesData;
import com.example.planesales.Aviasales.db.entity.FlightsForDates;
import com.example.planesales.Aviasales.schema.TicketsForSpecificDatesResponse;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.postgresql.util.PSQLException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class FlightDataManagementService {

    private final ModelMapper modelMapper = new ModelMapper();
    private final FlightsForDatesRepository flightsForDatesRepository;


    @PostConstruct
    public void setupModelMapper() {
        TypeMap<FlightsForDates, TicketsForSpecificDatesData> propertyMapper = this.modelMapper.createTypeMap(FlightsForDates.class, TicketsForSpecificDatesData.class);
    }

    public void proceedFlightData(Flux<TicketsForSpecificDatesResponse> response) {
        List<TicketsForSpecificDatesData> list = Objects.requireNonNull(response.blockFirst()).getData();
        for (TicketsForSpecificDatesData data : list) {
            if (flightsForDatesRepository.existsByLink(data.getLink())){
                return;
            }
            FlightsForDates flight = modelMapper.map(data, FlightsForDates.class);
            flightsForDatesRepository.save(flight);
        }
    }
}
