package com.example.AirlineBooking.controller;


import com.example.AirlineBooking.entity.Ticket;
import com.example.AirlineBooking.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private TicketService ticketService;

    @PostMapping("/add")
    public ResponseEntity<Ticket> bookTicket(@RequestBody @Valid Ticket ticket) {
        try {
            Ticket bookedTicket = ticketService.bookTicket(ticket);
            return ResponseEntity.status(HttpStatus.CREATED).body(bookedTicket);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // API hủy vé
    @DeleteMapping("/{ticketId}")
    public ResponseEntity<String> cancelTicket(@PathVariable String ticketId) {
        try {
            ticketService.cancelTicket(ticketId);
            return ResponseEntity.ok("Ticket cancelled successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // API lấy thông tin vé theo ID
    @GetMapping("/{ticketId}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable String ticketId) {
        try {
            Ticket ticket = ticketService.getTicketById(ticketId);
            return ResponseEntity.ok(ticket);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // API lấy danh sách vé theo mã chuyến bay
    @GetMapping("/flight/{flightCode}")
    public ResponseEntity<List<Ticket>> getTicketsByFlight(@PathVariable String flightCode) {
        List<Ticket> tickets = ticketService.getTicketsByFlight(flightCode);
        return ResponseEntity.ok(tickets);
    }

    // API lấy tất cả vé đã đặt
    @GetMapping("/all")
    public ResponseEntity<List<Ticket>> getAllTickets() {
        List<Ticket> tickets = ticketService.getAllTickets();
        return ResponseEntity.ok(tickets);
    }
}
