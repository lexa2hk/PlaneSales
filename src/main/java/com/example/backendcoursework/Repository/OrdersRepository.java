package com.example.backendcoursework.Repository;

import com.example.backendcoursework.Entity.Orders;
import org.springframework.data.repository.CrudRepository;

public interface OrdersRepository extends CrudRepository<Orders, Integer> {
}