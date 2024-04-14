package com.example.planesales.Aviasales.service;

import com.example.planesales.Aviasales.schema.IataEntity;
import com.example.planesales.Aviasales.schema.IataResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.annotation.PostConstruct;
import lombok.experimental.UtilityClass;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;

@Service
public class IataService {

    private HashMap<String, String> airlines;

    @PostConstruct
    private void init() throws IOException, InterruptedException {
        airlines = new HashMap<>();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://iata-and-icao-codes.p.rapidapi.com/airlines"))
                .header("X-RapidAPI-Key", "c360e301c8mshb7c18fed609b442p13c682jsn26d1c1bf0c31")
                .header("X-RapidAPI-Host", "iata-and-icao-codes.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        Gson gson = new Gson();
        List<IataEntity> iataResponse = gson.fromJson(response.body(), new TypeToken<List<IataEntity>>(){}.getType());
        iataResponse.forEach(iataEntity -> {
           airlines.put(iataEntity.getIata_code().toLowerCase(), iataEntity.getName());
        });
    }

    public String getAirlineName(String iataCode) {
        return airlines.getOrDefault(iataCode.toLowerCase(), null);
    }
}
