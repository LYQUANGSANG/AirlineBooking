package com.example.AirlineBooking.service;

import com.example.AirlineBooking.entity.Flight;
import com.example.AirlineBooking.repository.FlightRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {

    @Autowired
    private FlightRepository  flightRepository;


    //thêm chuyến bay
    public Flight addFlight(Flight fight){
        return flightRepository.save(fight);
    }

    //update thông tin chuyến bay

    public Flight updateFlight(String flightCode, Flight flightDetails ) throws Exception{
        Flight flight = flightRepository.findById(flightCode).orElseThrow(() -> new Exception("Flight not found"));

        flight.setDeparture(flightDetails.getDeparture());
        flight.setDestination(flightDetails.getDestination());
        flight.setDepartureTime(flightDetails.getDepartureTime());
        flight.setArrivalTime(flightDetails.getArrivalTime());
        flight.setAvailableSeats(flightDetails.getAvailableSeats());

        return flightRepository.save(flight);
    }

    //lấy thông tin chuyến bay

    public Flight getFlightById(String flightCode, @Valid Flight flightDetails) throws Exception {
        return flightRepository.findById(flightCode)
                .orElseThrow(() -> new Exception("Flight not found"));
    }

    //xóa chuyến bay

    public void deleteFlight(String flightCode) throws Exception {
        Flight flight = flightRepository.findById(flightCode)
                .orElseThrow(() -> new Exception("Flight not found"));
        flightRepository.delete(flight);
    }

    //lấy list tất cả chuyến bay
     public List<Flight> getAllFlight(){
        return flightRepository.findAll();
     }

     //Tìm chuyến bay theo điểm đi và điểm đến
    public List<Flight> findFlight(String departure, String destination){
        return flightRepository.findByDepartureAndDestination(departure, destination);
    }

}
