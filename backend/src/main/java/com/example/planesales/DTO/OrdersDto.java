package com.example.planesales.DTO;

import com.example.planesales.Entity.PaymentState;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * DTO for {@link com.example.planesales.Entity.Orders}
 */
@Value
@Builder
public class OrdersDto implements Serializable {
    int idOrder;
    Date creationDate;
    String paymentMethod;
    String flightRoute;
    PaymentState paymentStatus;
    List<TicketDto> tickets;
}