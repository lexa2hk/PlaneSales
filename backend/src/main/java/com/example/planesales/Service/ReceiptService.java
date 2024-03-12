package com.example.planesales.Service;

import com.example.planesales.Entity.Orders;
import com.example.planesales.Entity.Receipt;
import com.example.planesales.Repository.ReceiptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class ReceiptService {

    private final ReceiptRepository receiptRepository;


    public Receipt createReciept(Orders order){
        Receipt receipt = new Receipt();
        receipt.setUser(order.getUser());
        receipt.setPaymentStatus(order.getPaymentStatus());
        receipt.setCloseTime(new Date());
        receipt.setTotal(order.getTickets().size());
        return receiptRepository.save(receipt);
    }
}
