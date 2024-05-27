package com.example.eksamensprojektbilabonnement.repositories;

import com.example.eksamensprojektbilabonnement.models.LeaseAgreement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


/**
 * The Lease repository.
 */
@Repository
public class LeaseRepository {

    /**
     * The Jdbc template.
     */
    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * Create lease.
     * @author Hasan, Otto
     *
     * @param chassisNumber the chassis number
     * @param customerId    the customer id
     * @param startDate     the start date
     * @param endDate       the end date
     * @param terms         the terms
     */
    public void createLease(String chassisNumber, int customerId, LocalDate startDate, LocalDate endDate, String terms) {
        String  query = "INSERT INTO lease_agreements (chassis_number, customer_id, start_date, end_date, terms) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, chassisNumber, customerId, startDate, endDate, terms);
    }

    /**
     * Gets leases.
     * @author Hasan, Otto
     *
     * @return the leases
     */
    public List<LeaseAgreement> getLeases() {
        String query = "SELECT * FROM lease_agreements";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(LeaseAgreement.class));
    }


    /**
     * Gets active lease.
     * @author Magne
     *
     * @param chassisNumber the chassis number
     * @return the active lease
     */
    public LeaseAgreement getActiveLease(String chassisNumber) {
        String query = "SELECT * FROM lease_agreements WHERE chassis_number = ? AND is_concluded = 'FALSE'";
        return jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(LeaseAgreement.class), chassisNumber);
    }

    /**
     * Conclude lease.
     * @author Hasan
     *
     * @param leaseId the lease id
     */
    public void concludeLease(int leaseId) {
        String query = "UPDATE lease_agreements SET is_concluded = TRUE WHERE lease_id = ?";
        jdbcTemplate.update(query, leaseId);
    }

    /**
     * Gets lease.
     * @author Hasan
     *
     * @param leaseId the lease id
     * @return the lease
     */
    public LeaseAgreement getLease(int leaseId) {
        String query = "SELECT * FROM lease_agreements WHERE lease_id = ?";
        return jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(LeaseAgreement.class), leaseId);
    }

    /**
     * Gets non concluded leases.
     * @author Hasan
     *
     * @param chassisNumber the chassis number
     * @return the non concluded leases
     */
    public List<LeaseAgreement> getNonConcludedLeases(String chassisNumber) {
        String query = "SELECT * FROM lease_agreements WHERE chassis_number = ? AND is_concluded = FALSE";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(LeaseAgreement.class), chassisNumber);
    }

    public List<LeaseAgreement> getNonConcludedLeases(int customerId) {
        String query = "SELECT * FROM lease_agreements WHERE customer_id = ? AND is_concluded = FALSE";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(LeaseAgreement.class), customerId);
    }
}
