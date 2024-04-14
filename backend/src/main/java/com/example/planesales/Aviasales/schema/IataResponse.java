package com.example.planesales.Aviasales.schema;

import lombok.Getter;

import java.util.List;

@Getter
public class IataResponse {
    List<IataEntity> airlines;
}