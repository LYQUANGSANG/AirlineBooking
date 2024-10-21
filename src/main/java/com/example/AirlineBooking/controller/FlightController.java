package com.example.AirlineBooking.controller;

import com.example.AirlineBooking.entity.Flight;
import com.example.AirlineBooking.service.FlightService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    private FlightService flightService;


    @PostMapping("/add")
    public ResponseEntity<Flight> addFlight(@RequestBody @Valid Flight flight){
        Flight saveFlight = flightService.addFlight(flight);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveFlight);
    }

    @PutMapping("/{flightCode}")
    public ResponseEntity<Flight> updateFlight(@PathVariable String flightCode, @RequestBody @Valid Flight flightDetails){
        try {
            Flight updateFlight = flightService.getFlightById(flightCode, flightDetails);
            return ResponseEntity.ok(updateFlight);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{flightCode}")
    public ResponseEntity<String> deleteFlight(@PathVariable String fightCode){
        try {
            flightService.deleteFlight(fightCode);
            return ResponseEntity.ok("Flight delete successfully");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlight(){
        List<Flight> flights = flightService.getAllFlight();
        return ResponseEntity.ok(flights);
    }


    @GetMapping("/search")
    public ResponseEntity<List<Flight>> findFlight(
            @RequestParam String depature,
            @RequestParam String destination){
        List<Flight> flights = flightService.findFlight(depature, destination);
        return ResponseEntity.ok(flights);
    }
}
