package com.example.planesales.DTO;

import com.example.planesales.Entity.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class DtoBuilder {


    public OrdersDto buildOrdersDto(@NotNull Orders order) {
        return OrdersDto.builder()
                .idOrder(order.getIdOrder())
                .creationDate(order.getCreationDate())
                .paymentMethod(order.getPaymentMethod())
                .flightRoute(order.getFlightRoute())
                .paymentStatus(order.getPaymentStatus())
                .tickets(buildTicketsDtoList(order.getTickets()))
                .build();
    }

    private List<TicketDto> buildTicketsDtoList(@NotNull List<Ticket> tickets) {
        return tickets.stream().map((ticket) -> TicketDto.builder()
                .ticketLink(ticket.getTicketLink())
                .place(buildPlaceDto(ticket.getPlace()))
                .build()).toList();

    }

    private PlaceDto buildPlaceDto(@NotNull Place place) {
        return PlaceDto.builder()
                .classType(place.getClassType())
                .row_name(place.getRow_name())
                .plane(buildPlaneDto(place.getPlane()))
                .build();
    }

    private PlaneDto buildPlaneDto(@NotNull Plane plane) {
        return PlaneDto.builder()
                .model(plane.getModel())
                .capacity(plane.getCapacity())
                .calSign(plane.getCalSign())
                .flight(buildFlightDto(plane.getFlight()))
                .build();
    }

    private FlightDto buildFlightDto(@NotNull Flight flight) {
        return FlightDto.builder()
                .route(flight.getRoute())
                .passengerQty(flight.getPassengerQty())
                .duration(flight.getDuration())
                .companies(buildCompaniesDtoList(flight.getCompanies()))
                .build();
    }

    private List<CompanyDto> buildCompaniesDtoList(@NotNull List<Company> companies) {
        return companies.stream().map((company) -> CompanyDto.builder()
                .mainInfo(company.getMainInfo())
                .park(company.getPark())
                .since(company.getSince())
                .annualPassTraffic(company.getAnnualPassTraffic())
                .build()).toList();
    }

    private CompanyMarkDto buildCompanyMarkDto(@NotNull CompanyMark companyMark) {
        return new CompanyMarkDto(
                companyMark.getMark(),
                companyMark.getMarkText(),
                companyMark.getCompany().getCompanyName()
        );
   }
}
