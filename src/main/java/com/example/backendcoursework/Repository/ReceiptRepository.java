package com.example.backendcoursework.Repository;

import com.example.backendcoursework.Entity.Receipt;
import org.springframework.data.repository.CrudRepository;

public interface ReceiptRepository extends CrudRepository<Receipt, Integer> {
}