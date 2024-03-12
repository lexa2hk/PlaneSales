package com.example.planesales.Repository;

import com.example.planesales.Entity.Receipt;
import org.springframework.data.repository.CrudRepository;

public interface ReceiptRepository extends CrudRepository<Receipt, Integer> {
}