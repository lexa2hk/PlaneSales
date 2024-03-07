package com.example.backendcoursework.Service;

import com.example.backendcoursework.Entity.Orders;
import com.example.backendcoursework.Entity.Receipt;
import com.example.backendcoursework.Repository.ReceiptRepository;
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
