package com.example.planesales.Aviasales.configuration;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.SSLException;

@Configuration
public class AviasalesWebClientConfiguration {

    @Value("${travelpayouts.token}")
    private String travelpayoutsToken;

    @Bean
    public WebClient webClient() throws SSLException {
        final int size = 16 * 1024 * 1024;
        final ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(size))
                .build();

        SslContext sslContext = SslContextBuilder
                .forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build();

        HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(sslContext));


        WebClient webClient = WebClient
                .builder()
                .baseUrl("http://api.travelpayouts.com/aviasales")
                .exchangeStrategies(strategies)
                .defaultHeader("X-Access-Token", travelpayoutsToken)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
        return webClient;
    }

}
