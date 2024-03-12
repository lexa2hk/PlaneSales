package com.example.planesales.Controller.Request;
import lombok.Value;

@Value
public class ReviewRequest {
    String username;
    String companyName;
    String mark;
    String markText;
}
