package com.example.eksamensprojektbilabonnement.repositories;

import com.example.eksamensprojektbilabonnement.models.LeaseAgreement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class LeaseRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void createLease(String chassisNumber, int customerId, LocalDate startDate, LocalDate endDate, String terms) {
        String  query = "INSERT INTO lease_agreements (chassis_number, customer_id, start_date, end_date, terms) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, chassisNumber, customerId, startDate, endDate, terms);
    }

    public List<LeaseAgreement> getLeases() {
        String query = "SELECT * FROM lease_agreements";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(LeaseAgreement.class));
    }


    public LeaseAgreement getActiveLease(String chassisNumber) {
        String query = "SELECT * FROM lease_agreements WHERE chassis_number = ? AND is_concluded = 'FALSE'";
        return jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(LeaseAgreement.class), chassisNumber);
    }

    public void concludeLease(int leaseId) {
        String query = "UPDATE lease_agreements SET is_concluded = TRUE WHERE lease_id = ?";
        jdbcTemplate.update(query, leaseId);
    }

    public LeaseAgreement getLease(int leaseId) {
        String query = "SELECT * FROM lease_agreements WHERE lease_id = ?";
        return jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(LeaseAgreement.class), leaseId);
    }

    public List<LeaseAgreement> getNonConcludedLeases(String chassisNumber) {
        String query = "SELECT * FROM lease_agreements WHERE chassis_number = ? AND is_concluded = FALSE";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(LeaseAgreement.class), chassisNumber);
    }
}
