package com.example.eksamensprojektbilabonnement.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public class LeaseRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void createLease(String carChassisNumber, int customerId, LocalDate startDate, LocalDate endDate, String terms) {
        String  query = "INSERT INTO lease_agreements (chassis_number, customer_id, start_date, end_date, terms) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, carChassisNumber, customerId, startDate, endDate, terms);
    }
}
