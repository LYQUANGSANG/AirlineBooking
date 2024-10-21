package com.example.AirlineBooking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer")
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @NotNull(message = "Customer ID cannot be null")
    @Size(min = 5, max = 20, message = "Customer ID must be between 5 and 20 characters")
    private String id;

    @NotNull(message = "Customer name cannot be null")
    @Size(min = 2, max = 100, message = "Customer name must be between 2 and 100 characters")
    private String name;

    @NotNull(message = "Address cannot be null")
    @Size(min = 5, max = 255, message = "Address must be between 5 and 255 characters")
    private String address;

    @NotNull(message = "Phone number cannot be null")
    @Pattern(regexp = "^\\d{10,15}$", message = "Phone number must be between 10 and 15 digits")
    private String phone;


    public @NotNull(message = "Customer ID cannot be null") @Size(min = 5, max = 20, message = "Customer ID must be between 5 and 20 characters") String getId() {
        return id;
    }

    public void setId(@NotNull(message = "Customer ID cannot be null") @Size(min = 5, max = 20, message = "Customer ID must be between 5 and 20 characters") String id) {
        this.id = id;
    }

    public @NotNull(message = "Customer name cannot be null") @Size(min = 2, max = 100, message = "Customer name must be between 2 and 100 characters") String getName() {
        return name;
    }

    public void setName(@NotNull(message = "Customer name cannot be null") @Size(min = 2, max = 100, message = "Customer name must be between 2 and 100 characters") String name) {
        this.name = name;
    }

    public @NotNull(message = "Address cannot be null") @Size(min = 5, max = 255, message = "Address must be between 5 and 255 characters") String getAddress() {
        return address;
    }

    public void setAddress(@NotNull(message = "Address cannot be null") @Size(min = 5, max = 255, message = "Address must be between 5 and 255 characters") String address) {
        this.address = address;
    }

    public @NotNull(message = "Phone number cannot be null") @Pattern(regexp = "^\\d{10,15}$", message = "Phone number must be between 10 and 15 digits") String getPhone() {
        return phone;
    }

    public void setPhone(@NotNull(message = "Phone number cannot be null") @Pattern(regexp = "^\\d{10,15}$", message = "Phone number must be between 10 and 15 digits") String phone) {
        this.phone = phone;
    }
}
