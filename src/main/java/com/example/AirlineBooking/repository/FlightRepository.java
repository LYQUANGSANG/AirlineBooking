package com.example.AirlineBooking.repository;

import com.example.AirlineBooking.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, String> {
    List<Flight> findByDepartureAndDestination(String departure, String destination);
}
