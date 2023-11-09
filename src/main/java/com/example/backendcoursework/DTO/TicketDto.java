package com.example.backendcoursework.DTO;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.backendcoursework.Entity.Ticket}
 */
@Value
@Builder
public class TicketDto implements Serializable {
    String ticketLink;
    PlaceDto place;
}