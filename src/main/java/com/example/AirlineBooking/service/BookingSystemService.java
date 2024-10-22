package com.example.AirlineBooking.service;

import com.example.AirlineBooking.entity.Customer;
import com.example.AirlineBooking.entity.Flight;
import com.example.AirlineBooking.entity.Ticket;
import com.example.AirlineBooking.repository.CustomerRepository;
import com.example.AirlineBooking.repository.FlightRepository;
import com.example.AirlineBooking.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class BookingSystemService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TicketRepository ticketRepository;


    //book vé
    public Ticket bookTicket(String flightCode, String customerId, String seatNumber) throws Exception {
        Flight flight = flightRepository.findById(flightCode)
                .orElseThrow(() -> new Exception("Flight not found"));

        if (flight.getAvailableSeats() <= 0) {
            throw new Exception("No seats available");
        }

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new Exception("Customer not found"));

        Ticket ticket = new Ticket();
        ticket.setTicketId(UUID.randomUUID().toString());
        ticket.setFlight(flight);
        ticket.setCustomer(customer);
        ticket.setSeatNumber(seatNumber);
        ticket.setBookingTime(LocalDateTime.now());

        flight.setAvailableSeats(flight.getAvailableSeats() - 1);

        ticketRepository.save(ticket);
        flightRepository.save(flight);

        return ticket;
    }

    // Hủy vé
    public void cancelTicket(String ticketId) throws Exception {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new Exception("Ticket not found"));

        Flight flight = ticket.getFlight();
        flight.setAvailableSeats(flight.getAvailableSeats() + 1);

        ticketRepository.delete(ticket);
        flightRepository.save(flight);
    }

    // Báo cáo số lượng vé đã bán cho mỗi chuyến bay
    public int getSoldTicketsForFlight(String flightCode) {
        List<Ticket> tickets = ticketRepository.findByFlight_FlightCode(flightCode);
        return tickets.size();
    }



    public double getCancellationRateForFlight(String flightCode) {
        // Số vé bị hủy cho chuyến bay
        int cancelledTickets = ticketRepository.countByFlight_FlightCodeAndIsCancelledTrue(flightCode);
        // Tổng số vé đã bán cho chuyến bay (bao gồm cả vé đã hủy và chưa hủy)
        int totalTickets = ticketRepository.countByFlight_FlightCode(flightCode);

        // Tránh chia cho 0 nếu không có vé nào được bán
        if (totalTickets == 0) {
            return 0;
        }
        return ((double) cancelledTickets / totalTickets) * 100;
    }


    // Hàm tính tỷ lệ hủy vé trên tất cả các chuyến bay trong hệ thống
    public double getCancellationRateForAllFlights() {
        // Tổng số vé bị hủy trên tất cả các chuyến bay
        int totalCancelledTickets = ticketRepository.countByIsCancelledTrue();
        // Tổng số vé đã bán trên tất cả các chuyến bay
        long totalSoldTickets = ticketRepository.count();

        // Tránh chia cho 0 nếu không có vé nào được bán
        if (totalSoldTickets == 0) {
            return 0;
        }

        return ((double) totalCancelledTickets / totalSoldTickets) * 100;
    }


}
