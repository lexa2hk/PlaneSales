package com.example.backendcoursework.Controller.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {
    private String paymentMethod;
    private String flightRoute;

}
