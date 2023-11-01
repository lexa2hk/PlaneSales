package com.example.backendcoursework.Repository;

import com.example.backendcoursework.Entity.Orders;
import com.example.backendcoursework.Entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OrdersRepository extends CrudRepository<Orders, Integer> {
    Optional<Orders> findByUser(User user);
}