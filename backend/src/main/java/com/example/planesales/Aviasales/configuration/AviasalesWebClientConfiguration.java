package com.example.planesales.Aviasales.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AviasalesWebClientConfiguration {

    @Value("${travelpayouts.token}")
    private String travelpayoutsToken;

    @Bean
    public WebClient webClient() {
        final int size = 16 * 1024 * 1024;
        final ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(size))
                .build();

        WebClient webClient = WebClient
                .builder()
                .baseUrl("https://api.travelpayouts.com/aviasales")
                .exchangeStrategies(strategies)
                .defaultHeader("X-Access-Token", travelpayoutsToken)
                .build();
        return webClient;
    }

}
