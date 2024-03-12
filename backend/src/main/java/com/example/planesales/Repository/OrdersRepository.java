package com.example.planesales.Repository;

import com.example.planesales.Entity.Orders;
import com.example.planesales.Entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.List;

public interface OrdersRepository extends CrudRepository<Orders, Integer> {
    Optional<Orders> findByUser(User user);

    List<Orders> findAllByUser(User user);


    List<Orders> findAllByTickets_Place_Plane_FlightRoute(String route);

    @Query("""
            SELECT o FROM Orders o          
            
""")
    List<Orders> customFindAllByRoute(@Param("route") String route);
}