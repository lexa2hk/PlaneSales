package com.example.backendcoursework.Controller.Response;


import com.example.backendcoursework.Entity.PaymentState;
import lombok.Value;

public record PaymentStatusResponse(PaymentState paymentStatus) {
}
