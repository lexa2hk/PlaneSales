package com.example.backendcoursework.Service;

import com.example.backendcoursework.Entity.*;
import com.example.backendcoursework.Exception.OrderNotFoundException;
import com.example.backendcoursework.Exception.OrderNotPaidException;
import com.example.backendcoursework.Repository.OrdersRepository;
import com.example.backendcoursework.Repository.PlaceRepository;
import com.example.backendcoursework.Repository.PlaneRepository;
import com.example.backendcoursework.Repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrdersRepository ordersRepository;
    private final TicketRepository ticketRepository;
    private final PlaceRepository placeRepository;
    private final PlaneRepository planeRepository;
    private final ReceiptService receiptService;


    //todo check if flight actually exist
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
        List<Orders> orderOptional = ordersRepository.findAllByUser(user);
        for (Orders order : orderOptional) {
            order.setPaymentStatus(PaymentState.PAID);
            ordersRepository.save(order);
            return;
        }
//        if (!orderOptional.isEmpty()) {
//            for (Orders order : orderOptional) {
//                order.setPaymentStatus(PaymentState.PAID);
//                return order;
//            }
//        }
//        throw new OrderNotFoundException("username: " + user.getUsername());
    }

    public void payOrder(User user, String flightRoute) throws OrderNotFoundException {
        List<Orders> orderOptional = ordersRepository.findAllByUser(user);
        if (!orderOptional.isEmpty()) {
            for (Orders order : orderOptional) {
                if (order.getFlightRoute().equals(flightRoute)) {
                    order.setPaymentStatus(PaymentState.PAID);
                    return;
                }
            }

        } else {
            throw new OrderNotFoundException("username: " + user.getUsername());
        }
        return;
    }

//    public Receipt payOrder(int orderId) throws OrderNotFoundException {
//        Optional<Orders> orderOptional = ordersRepository.findById(orderId);
//
//        if (orderOptional.isPresent()) {
//            Orders order = orderOptional.get();
//            order.setPaymentStatus(PaymentState.PAID);
//            return receiptService.createReciept(order);
//        } else {
//            throw new OrderNotFoundException("orderId: " + orderId);
//        }
//    }


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
                //todo generate pdf ticket or webpage
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
        place.setRow_name(randomizeRowName());


        place.setPlane(planeRepository.findAllByFlight_Route(flightRoute).iterator().next());
        return placeRepository.save(place);
    }

    private String randomizeRowName() {
        Random random = new Random();
        char randomLetter = (char) ('A' + random.nextInt(26));
        int randomSeatNumber = 1 + random.nextInt(30);
        return randomSeatNumber + String.valueOf(randomLetter);
    }


}
