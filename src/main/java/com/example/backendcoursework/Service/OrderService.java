package com.example.backendcoursework.Service;

import com.example.backendcoursework.Entity.*;
import com.example.backendcoursework.Exception.OrderNotFoundException;
import com.example.backendcoursework.Exception.OrderNotPaidException;
import com.example.backendcoursework.Repository.OrdersRepository;
import com.example.backendcoursework.Repository.PlaceRepository;
import com.example.backendcoursework.Repository.PlaneRepository;
import com.example.backendcoursework.Repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class OrderService {

    private final OrdersRepository ordersRepository;
    private final TicketRepository ticketRepository;
    private final PlaceRepository placeRepository;
    private final PlaneRepository planeRepository;


    public OrderService(OrdersRepository ordersRepository,
                        TicketRepository ticketRepository,
                        PlaceRepository placeRepository,
                        PlaneRepository planeRepository) {
        this.ordersRepository = ordersRepository;
        this.ticketRepository = ticketRepository;
        this.placeRepository = placeRepository;
        this.planeRepository = planeRepository;
    }


    public Orders createOrder(User user,String paymentMethod, String flightRoute){
        Orders order = new Orders();
        order.setUser(user);
        order.setPaymentMethod(paymentMethod);
        order.setFlightRoute(flightRoute);
        order.setPaymentStatus(PaymentState.NOT_PAID);
        order.setCreationDate(new Date());
//
//        checkPayment(order,flightRoute);

        return ordersRepository.save(order);
    }

    public void payOrder(User user) throws OrderNotFoundException {
        Optional<Orders> orderOptional = ordersRepository.findByUser(user);
        if (orderOptional.isPresent()) {
            Orders order = orderOptional.get();
            order.setPaymentStatus(PaymentState.PAID);
        } else {
            throw new OrderNotFoundException("username: " + user.getUsername());
        }
    }

    public void payOrder(int orderId) throws OrderNotFoundException {
        Optional<Orders> orderOptional = ordersRepository.findById(orderId);

        if (orderOptional.isPresent()) {
            Orders order = orderOptional.get();
            order.setPaymentStatus(PaymentState.PAID);
        } else {
            throw new OrderNotFoundException("orderId: " + orderId);
        }
    }


    public boolean checkPayment(int orderId) throws OrderNotFoundException {
        Optional<Orders> orderOptional = ordersRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            Orders order = orderOptional.get();
            return order.getPaymentStatus().equals(PaymentState.PAID);
        }else{
            throw new OrderNotFoundException("orderId: " + orderId);
        }
    }

    public Ticket createTicket(int orderId) throws OrderNotFoundException, OrderNotPaidException {
        Optional<Orders> orderOptional = ordersRepository.findById(orderId);
        Ticket ticket = new Ticket();
        if (orderOptional.isPresent()) {
            Orders order = orderOptional.get();
            if(checkPayment(orderId)){
                ticket.setOrder(order);
                //todo generate pdf ticket as mock
                ticket.setTicketLink("https://www.google.com");
                ticketRepository.save(ticket);
                ticket.setPlace(reservePlace(ticket, order.getFlightRoute()));
                return ticket;
            }else{
                throw new OrderNotPaidException("orderId: " + orderId);
            }


        } else {
            throw new OrderNotFoundException("orderId: " + ticket.getOrder().getIdOrder());
        }
    }


    private Place reservePlace(Ticket ticket, String flightRoute) {
        Place place = new Place();
        place.setClassType("First");
        //todo randomize row name
        place.setRow_name("21A");

        place.setPlane(planeRepository.findAllByFlight_Route(flightRoute).iterator().next());
        return placeRepository.save(place);
    }


}
