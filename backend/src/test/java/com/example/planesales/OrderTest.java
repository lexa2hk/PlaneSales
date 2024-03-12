package com.example.planesales;

import com.example.planesales.Entity.*;
import com.example.planesales.Exception.OrderNotFoundException;
import com.example.planesales.Repository.*;
import com.example.planesales.Service.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class OrderTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrdersRepository ordersRepository;

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private PlaceRepository placeRepository;

    @Mock
    private PlaneRepository planeRepository;



    @Test
    public void testCreateOrder() {
        // Arrange
        User user = new User();
        String paymentMethod = "Credit Card";
        String flightRoute = "ABC123";

        when(flightRepository.existsByRoute(flightRoute)).thenReturn(true);
        when(ordersRepository.save(org.mockito.ArgumentMatchers.any(Orders.class))).thenAnswer(invocation -> {
            Orders order = invocation.getArgument(0);
            order.setIdOrder(1); // Simulate order creation
            return order;
        });

        // Act
        Orders createdOrder = orderService.createOrder(user, paymentMethod, flightRoute);

        // Assert
        assertEquals(PaymentState.NOT_PAID, createdOrder.getPaymentStatus());
        // Add more assertions as needed
    }

    @Test
    public void testCreateOrder_InvalidFlightRoute() {
        // Arrange
        User user = new User();
        String paymentMethod = "Credit Card";
        String flightRoute = "XYZ789";

        when(flightRepository.existsByRoute(flightRoute)).thenReturn(false);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> orderService.createOrder(user, paymentMethod, flightRoute));
    }

//    @Test
//    public void testPayOrder() throws OrderNotFoundException, OrderNotPaidException {
//        // Arrange
//        User user = new User();
//        Orders order = new Orders();
//        order.setUser(user);
//        order.setPaymentStatus(PaymentState.NOT_PAID);
//        order.setIdOrder(1);
//
//        when(ordersRepository.findAllByUser(user)).thenReturn(Collections.singletonList(order));
//        when(ordersRepository.findById(1)).thenReturn(Optional.of(order));
//        when(planeRepository.findByFlightCode(null).iterator().next()).thenReturn(new Plane());
//
//        when(ticketRepository.save(org.mockito.ArgumentMatchers.any(Ticket.class))).thenAnswer(invocation -> {
//            Ticket ticket = invocation.getArgument(0);
//            ticket.setIdTicket(1);
//            ticket.setOrder(order);
//            return ticket;
//        });
//
//
//
//
//        // Act
//        orderService.payOrder(user);
//
//        // Assert
//        assertEquals(PaymentState.PAID, order.getPaymentStatus());
//        assertEquals(1, order.getTickets().size()); // Ensure a ticket is created
//        // Add more assertions as needed
//    }


    @Test
    public void testPayOrder_NoOrdersFound() {
        // Arrange
        User user = new User();
        user.setEmail("example@mail.com");
        when(ordersRepository.findAllByUser(user)).thenReturn(Collections.emptyList());

        // Act and Assert
        assertThrows(OrderNotFoundException.class, () -> orderService.payOrder(user));
    }

}
