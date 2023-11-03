package com.example.backendcoursework.Controller;

import com.example.backendcoursework.Controller.Request.OrderRequest;
import com.example.backendcoursework.Entity.Orders;
import com.example.backendcoursework.Entity.PaymentState;
import com.example.backendcoursework.Entity.User;
import com.example.backendcoursework.Exception.OrderNotFoundException;
import com.example.backendcoursework.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    //todo create dto for orders

    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@AuthenticationPrincipal UserDetails userDetails,
                                              @RequestBody OrderRequest request){
        Orders order = orderService.createOrder((User) userDetails, request.getPaymentMethod(), request.getFlightRoute());

        return ResponseEntity.ok("Order created successfully");
    }

    @PostMapping("/pay")
    public ResponseEntity<String> payOrder(@AuthenticationPrincipal UserDetails userDetails,
                                           @RequestBody OrderRequest request){
        try {
            orderService.payOrder((User) userDetails);
            return ResponseEntity.ok("Success paid all your orders");
        } catch (OrderNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //todo create dto for status
    @GetMapping("/status")
    public ResponseEntity<PaymentState> getOrderStatus(@AuthenticationPrincipal UserDetails userDetails,
                                                       @RequestParam int orderId){
        PaymentState status;
        try {
            if(orderService.checkPayment(orderId))
                status = PaymentState.PAID;
            else
                status = PaymentState.NOT_PAID;
        } catch (OrderNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(status);
    }

}
