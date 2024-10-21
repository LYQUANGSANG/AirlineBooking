package com.example.AirlineBooking.repository;

import com.example.AirlineBooking.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
