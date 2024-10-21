package com.example.AirlineBooking.service;

import com.example.AirlineBooking.entity.Customer;
import com.example.AirlineBooking.entity.Flight;
import com.example.AirlineBooking.entity.Ticket;
import com.example.AirlineBooking.repository.CustomerRepository;
import com.example.AirlineBooking.repository.FlightRepository;
import com.example.AirlineBooking.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private CustomerRepository customerRepository;

    // Đặt vé
    public Ticket bookTicket(Ticket ticket) throws Exception {
        Flight flight = flightRepository.findById(ticket.getFlight().getFlightCode())
                .orElseThrow(() -> new Exception("Flight not found"));

        if (flight.getAvailableSeats() <= 0) {
            throw new Exception("No seats available");
        }

        Customer customer = customerRepository.findById(ticket.getCustomer().getId())
                .orElseThrow(() -> new Exception("Customer not found"));

        ticket.setFlight(flight);
        ticket.setCustomer(customer);
        ticket.setBookingTime(LocalDateTime.now());
        flight.setAvailableSeats(flight.getAvailableSeats() - 1);

        return ticketRepository.save(ticket);
    }

    // Hủy vé
    public void cancelTicket(String ticketId) throws Exception {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new Exception("Ticket not found"));

        Flight flight = ticket.getFlight();
        flight.setAvailableSeats(flight.getAvailableSeats() + 1);
        ticketRepository.delete(ticket);
    }

    // Lấy thông tin vé theo ID
    public Ticket getTicketById(String ticketId) throws Exception {
        return ticketRepository.findById(ticketId)
                .orElseThrow(() -> new Exception("Ticket not found"));
    }

    // Lấy danh sách tất cả vé theo mã chuyến bay
    public List<Ticket> getTicketsByFlight(String flightCode) {
        return ticketRepository.findByFlight_FlightCode(flightCode);
    }

    // Lấy danh sách tất cả vé đã đặt
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }
}
