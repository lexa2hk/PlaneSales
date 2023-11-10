package com.example.backendcoursework.Controller;

import com.example.backendcoursework.Controller.Request.OrderRequest;
import com.example.backendcoursework.Controller.Response.PaymentStatusResponse;
import com.example.backendcoursework.DTO.DtoBuilder;
import com.example.backendcoursework.DTO.OrdersDto;
import com.example.backendcoursework.Entity.Flight;
import com.example.backendcoursework.Entity.Orders;
import com.example.backendcoursework.Entity.PaymentState;
import com.example.backendcoursework.Entity.User;
import com.example.backendcoursework.Exception.OrderNotFoundException;
import com.example.backendcoursework.Exception.OrderNotPaidException;
import com.example.backendcoursework.Service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Orders", description = "Orders API")
public class OrderController {

    private final OrderService orderService;
    private final DtoBuilder  dtoBuilder;

    @Operation(
            description = "Create an order",
            summary = "endpoint for creating an order",
            responses = {
                    @ApiResponse(description = "Successful order creation", responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = OrdersDto.class)))),

                    @ApiResponse(description = "Not authorized/Internal Server Error", responseCode = "403", content = @Content(schema = @Schema(implementation = String.class)))
            }
    )
    @PostMapping("/create")
    public ResponseEntity<OrdersDto> createOrder(@AuthenticationPrincipal UserDetails userDetails,
                                                 @RequestBody OrderRequest request){
        Orders order = orderService.createOrder((User) userDetails, request.getPaymentMethod(), request.getFlightRoute());

        return ResponseEntity.ok(dtoBuilder.buildOrdersDto(order));
    }

    @Operation(
            description = "Pay an order",
            summary = "endpoint for pay an order",
            responses = {
                    @ApiResponse(description = "Successful order payment", responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = String.class)))),

                    @ApiResponse(description = "Not authorized/Internal Server Error", responseCode = "403", content = @Content(schema = @Schema(implementation = String.class)))
            }
    )
    @PostMapping("/pay")
    public ResponseEntity<String> payOrder(@AuthenticationPrincipal UserDetails userDetails,
                                           @RequestBody OrderRequest request){
        try {
            orderService.payOrder((User) userDetails, request.getFlightRoute());

            return ResponseEntity.ok("Success paid all your orders");
        } catch (OrderNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (OrderNotPaidException e) {
            throw new RuntimeException(e);
        }
    }


    @Operation(
            description = "Get order status",
            summary = "Get order status",
            responses = {
                    @ApiResponse(description = "Successful", responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = PaymentStatusResponse.class)))),

                    @ApiResponse(description = "Not authorized/Internal Server Error", responseCode = "403", content = @Content(schema = @Schema(implementation = String.class)))
            }
    )
    @GetMapping("/status")
    public ResponseEntity<PaymentStatusResponse> getOrderStatus(@AuthenticationPrincipal UserDetails userDetails,
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

        return ResponseEntity.ok(new PaymentStatusResponse(status));
    }

    @Operation(
            description = "Get all orders",
            summary = "Get all orders of authorized user",
            responses = {
                    @ApiResponse(description = "Successful", responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = OrdersDto.class)))),

                    @ApiResponse(description = "Not authorized/Internal Server Error", responseCode = "403", content = @Content(schema = @Schema(implementation = String.class)))
            }
    )
    @GetMapping("/all")
    public ResponseEntity<List<OrdersDto>> findAllByUser(@AuthenticationPrincipal UserDetails userDetails){
        List<Orders> orders = orderService.findAllByUser((User) userDetails);
        List<OrdersDto> ordersDtoList = orders.stream()
                .map(dtoBuilder::buildOrdersDto)
                .toList();
        return ResponseEntity.ok(ordersDtoList);

    }

}
