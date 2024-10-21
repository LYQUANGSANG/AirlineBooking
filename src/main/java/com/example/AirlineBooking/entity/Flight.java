package com.example.AirlineBooking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "flight")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Flight {

    @Id
    @NotNull(message = "Flight code cannot be null")
    @Size(min = 3, max = 10, message = "Flight code must be between 3 and 10 characterss")
    private String flightCode;

    @NotNull(message = "Departure location cannot be null")
    @Size(min = 3, max = 100, message = "Departure location must be between 3 and 100 characters")
    private String departure;

    @NotNull(message = "Destination cannot be null")
    @Size(min = 3, max = 100, message = "Destination must be between 3 and 100 characters")
    private String destination;

    @NotNull(message = "Departure time cannot be null")
    private LocalDateTime departureTime;

    @NotNull(message = "Arrival time cannot be null")
    private LocalDateTime arrivalTime;

    @Min(value = 0, message = "Available seats must be a positive number")
    private int availableSeats;

    public @NotNull(message = "Flight code cannot be null") @Size(min = 3, max = 10, message = "Flight code must be between 3 and 10 characterss") String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(@NotNull(message = "Flight code cannot be null") @Size(min = 3, max = 10, message = "Flight code must be between 3 and 10 characterss") String flightCode) {
        this.flightCode = flightCode;
    }

    public @NotNull(message = "Departure location cannot be null") @Size(min = 3, max = 100, message = "Departure location must be between 3 and 100 characters") String getDeparture() {
        return departure;
    }

    public void setDeparture(@NotNull(message = "Departure location cannot be null") @Size(min = 3, max = 100, message = "Departure location must be between 3 and 100 characters") String departure) {
        this.departure = departure;
    }

    public @NotNull(message = "Destination cannot be null") @Size(min = 3, max = 100, message = "Destination must be between 3 and 100 characters") String getDestination() {
        return destination;
    }

    public void setDestination(@NotNull(message = "Destination cannot be null") @Size(min = 3, max = 100, message = "Destination must be between 3 and 100 characters") String destination) {
        this.destination = destination;
    }

    public @NotNull(message = "Departure time cannot be null") LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(@NotNull(message = "Departure time cannot be null") LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public @NotNull(message = "Arrival time cannot be null") LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(@NotNull(message = "Arrival time cannot be null") LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Min(value = 0, message = "Available seats must be a positive number")
    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(@Min(value = 0, message = "Available seats must be a positive number") int availableSeats) {
        this.availableSeats = availableSeats;
    }
}
