package com.example.AirlineBooking.repository;

import com.example.AirlineBooking.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, String> {
    List<Ticket> findByFlight_FlightCode(String flightCode);
}
