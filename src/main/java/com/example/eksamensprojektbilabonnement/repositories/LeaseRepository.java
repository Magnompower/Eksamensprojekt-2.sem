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
     *
     * @param chassisNumber the chassis number
     * @param customerId    the customer id
     * @param startDate     the start date
     * @param endDate       the end date
     * @author Hasan, Otto
     */
    public void createLease(String chassisNumber, int customerId, LocalDate startDate, LocalDate endDate ) {
        String  query = "INSERT INTO lease_agreements (chassis_number, customer_id, start_date, end_date) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(query, chassisNumber, customerId, startDate, endDate);
    }

    /**
     * Gets leases.
     *
     * @return the leases
     * @author Hasan, Otto
     */
    public List<LeaseAgreement> getLeases() {
        String query = "SELECT * FROM lease_agreements";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(LeaseAgreement.class));
    }


    /**
     * Gets active lease.
     *
     * @param chassisNumber the chassis number
     * @return the active lease
     * @author Magne
     */
    public LeaseAgreement getActiveLease(String chassisNumber) {
        String query = "SELECT * FROM lease_agreements WHERE chassis_number = ? AND is_active = 1";
        return jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(LeaseAgreement.class), chassisNumber);
    }

    /**
     * Conclude lease.
     *
     * @param leaseId the lease id
     * @author Hasan
     */
    public void concludeLease(int leaseId) {
        String query = "UPDATE lease_agreements SET is_concluded = TRUE WHERE lease_id = ?";
        jdbcTemplate.update(query, leaseId);
    }

    /**
     * Gets lease.
     *
     * @param leaseId the lease id
     * @return the lease
     * @author Hasan
     */
    public LeaseAgreement getLease(int leaseId) {
        String query = "SELECT * FROM lease_agreements WHERE lease_id = ?";
        return jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(LeaseAgreement.class), leaseId);
    }

    /**
     * Gets non concluded leases.
     *
     * @param chassisNumber the chassis number
     * @return the non concluded leases
     * @author Hasan
     */
    public List<LeaseAgreement> getNonConcludedLeases(String chassisNumber) {
        String query = "SELECT * FROM lease_agreements WHERE chassis_number = ? AND is_concluded = FALSE";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(LeaseAgreement.class), chassisNumber);
    }

    /**
     * Gets non concluded leases.
     *
     * @param customerId the customer id
     * @return the non concluded leases
     * @author Otto
     */
    public List<LeaseAgreement> getNonConcludedLeases(int customerId) {
        String query = "SELECT * FROM lease_agreements WHERE customer_id = ? AND is_concluded = FALSE";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(LeaseAgreement.class), customerId);
    }

    public void setLeaseInactive(int leaseId) {
        String query = "UPDATE lease_agreements SET is_active = 0 WHERE lease_id = ?";
        jdbcTemplate.update(query, leaseId);
    }
}
