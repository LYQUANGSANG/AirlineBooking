package com.example.AirlineBooking.controller;

import com.example.AirlineBooking.entity.Ticket;
import com.example.AirlineBooking.service.BookingSystemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class BookingSystemController {

    private BookingSystemService bookingSystemService;


    @PostMapping("/book")
    public ResponseEntity<Ticket> bookTicket(
            @RequestParam String flightCode,
            @RequestParam String customerId,
            @RequestParam String seatNumber) {
        try {
            Ticket ticket = bookingSystemService.bookTicket(flightCode, customerId, seatNumber);
            return ResponseEntity.status(HttpStatus.CREATED).body(ticket);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    //hủy vé
    @DeleteMapping("/cancel/{ticketId}")
    public ResponseEntity<String> cancelTicket(@PathVariable String ticketId) {
        try {
            bookingSystemService.cancelTicket(ticketId);
            return ResponseEntity.ok("Ticket cancelled successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @GetMapping("/report/sold/{flightCode}")
    public ResponseEntity<Long> getTotalTicketsSold(@PathVariable String flightCode) {
        long totalTickets = bookingSystemService.getSoldTicketsForFlight(flightCode);
        return ResponseEntity.ok(totalTickets);
    }

    // API trả về tỷ lệ hủy vé cho một chuyến bay cụ thể
    @GetMapping("/report/cancellation-rate/{flightCode}")
    public ResponseEntity<Double> getCancellationRateForFlight(@PathVariable String flightCode) {
        double cancellationRate = bookingSystemService.getCancellationRateForFlight(flightCode);
        return ResponseEntity.ok(cancellationRate);
    }


    // API trả về tỷ lệ hủy vé cho tất cả các chuyến bay
    @GetMapping("/report/cancellation-rate/all")
    public ResponseEntity<Double> getCancellationRateForAllFlights() {
        double cancellationRate = bookingSystemService.getCancellationRateForAllFlights();
        return ResponseEntity.ok(cancellationRate);
    }
}
