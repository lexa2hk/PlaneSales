package com.example.planesales.DTO;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.planesales.Entity.Ticket}
 */
@Value
@Builder
public class TicketDto implements Serializable {
    String ticketLink;
    PlaceDto place;
}