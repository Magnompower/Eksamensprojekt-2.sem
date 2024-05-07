package com.example.eksamensprojektbilabonnement.repositories;

import com.example.eksamensprojektbilabonnement.models.LeaseAgreement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class LeaseRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void createLease(String carChassisNumber, int customerId, LocalDate startDate, LocalDate endDate, String terms) {
        String  query = "INSERT INTO lease_agreements (chassis_number, customer_id, start_date, end_date, terms) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, carChassisNumber, customerId, startDate, endDate, terms);
    }

    public List<LeaseAgreement> getLeases() {
        String query = "SELECT * FROM lease_agreements";
        RowMapper<LeaseAgreement> rowMapper = new BeanPropertyRowMapper<>(LeaseAgreement.class);
        return jdbcTemplate.query(query, rowMapper);

    }
}
