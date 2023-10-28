package com.example.backendcoursework.Repository;

import com.example.backendcoursework.Entity.Ticket;
import org.springframework.data.repository.CrudRepository;

public interface TicketRepository extends CrudRepository<Ticket, Integer> {
}