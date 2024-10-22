package com.example.AirlineBooking.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @NotNull(message = "Ticket ID cannot be null")
    private String ticketId;

    @ManyToOne
    @JoinColumn(name = "flight_code")
    @NotNull(message = "Flight cannot be null")
    private Flight flight;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @NotNull(message = "Customer cannot be null")
    private Customer customer;

    @NotNull(message = "Seat number cannot be null")
    @Pattern(regexp = "^[A-Z][0-9]+$", message = "Seat number must be a valid format (e.g., A1, B2)")
    private String seatNumber;

    @NotNull(message = "Booking time cannot be null")
    private LocalDateTime bookingTime;

    private Boolean isCancelled;

    public @NotNull(message = "Ticket ID cannot be null") String getTicketId() {
        return ticketId;
    }

    public void setTicketId(@NotNull(message = "Ticket ID cannot be null") String ticketId) {
        this.ticketId = ticketId;
    }

    public @NotNull(message = "Flight cannot be null") Flight getFlight() {
        return flight;
    }

    public void setFlight(@NotNull(message = "Flight cannot be null") Flight flight) {
        this.flight = flight;
    }

    public @NotNull(message = "Customer cannot be null") Customer getCustomer() {
        return customer;
    }

    public void setCustomer(@NotNull(message = "Customer cannot be null") Customer customer) {
        this.customer = customer;
    }

    public @NotNull(message = "Seat number cannot be null") @Pattern(regexp = "^[A-Z][0-9]+$", message = "Seat number must be a valid format (e.g., A1, B2)") String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(@NotNull(message = "Seat number cannot be null") @Pattern(regexp = "^[A-Z][0-9]+$", message = "Seat number must be a valid format (e.g., A1, B2)") String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public @NotNull(message = "Booking time cannot be null") LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(@NotNull(message = "Booking time cannot be null") LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    public Boolean getCancelled() {
        return isCancelled;
    }

    public void setCancelled(Boolean cancelled) {
        isCancelled = cancelled;
    }
}
