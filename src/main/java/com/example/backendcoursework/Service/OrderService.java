package com.example.backendcoursework.Service;

import com.example.backendcoursework.Entity.Orders;
import com.example.backendcoursework.Entity.Place;
import com.example.backendcoursework.Entity.Ticket;
import com.example.backendcoursework.Entity.User;
import com.example.backendcoursework.Repository.OrdersRepository;
import com.example.backendcoursework.Repository.PlaceRepository;
import com.example.backendcoursework.Repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderService {

    private final OrdersRepository ordersRepository;
    private final TicketRepository ticketRepository;
    private final PlaceRepository placeRepository;


    public OrderService(OrdersRepository ordersRepository,
                        TicketRepository ticketRepository,
                        PlaceRepository placeRepository) {
        this.ordersRepository = ordersRepository;
        this.ticketRepository = ticketRepository;
        this.placeRepository = placeRepository;
    }

    //todo implement order user logic. it will also create seats and tickets
    public Orders createOrder(User user,String paymentMethod, String flightRoute){
        Orders order = new Orders();
        order.setUser(user);
        order.setPaymentMethod(paymentMethod);
//        order.setPaymentStatus(); //todo enumerated payment status
        order.setCreationDate(new Date());

        checkPayment(order,flightRoute);

        return ordersRepository.save(order);
    }


    public Ticket checkPayment(Orders order, String flightRoute){

        Ticket ticket = new Ticket();
        ticket.setOrder(order);
        ticket.setTicketLink("http://google.com");

        ticket.setPlace(this.reservePlace(ticket,flightRoute));
        return ticketRepository.save(ticket);
    }

    private Place reservePlace(Ticket ticket, String flightRoute) {
        Place place = new Place();
        place.setClassType("First");
        place.setRow_name(flightRoute);
        place.setPlane(ticket.getPlace().getPlane());
        return placeRepository.save(place);
    }
}
