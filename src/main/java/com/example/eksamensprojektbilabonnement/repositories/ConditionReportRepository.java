package com.example.eksamensprojektbilabonnement.repositories;

import com.example.eksamensprojektbilabonnement.models.ConditionReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


/**
 * The Condition report repository.
 */
@Repository
public class ConditionReportRepository {

    /**
     * The Jdbc template.
     */
    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * Create condition report.
     * @author Hasan
     *
     * @param chassisNumber the chassis number
     * @param leaseId       the lease id
     * @param kmBeforeLease the km before lease
     */
    public void createConditionReport(String chassisNumber, int leaseId, double kmBeforeLease) {
        String query = "INSERT INTO condition_reports (chassis_number, lease_id, km_before_lease)" +
                "VALUES (?,?,?)";
        jdbcTemplate.update(query, chassisNumber, leaseId, kmBeforeLease);
    }


    /**
     * Gets condition report.
     * @author Hasan
     *
     * @param leaseId the lease id
     * @return the condition report
     */
    public ConditionReport getConditionReport(int leaseId) {
        String query = "SELECT * FROM condition_reports WHERE lease_id = ?";
        return jdbcTemplate.queryForObject(query,BeanPropertyRowMapper.newInstance(ConditionReport.class), leaseId);
    }

    /**
     * Sets km driven after lease.
     * @author Hasan
     *
     * @param leaseId  the lease id
     * @param kmDriven the km driven
     */
    public void setKmDrivenAfterLease(int leaseId, double kmDriven) {
        String query = "UPDATE condition_reports SET km_after_lease = ? WHERE lease_id = ?";
        jdbcTemplate.update(query, kmDriven, leaseId);


    }


    public void addPriceToTotalCost(double damagePrice, int leaseId) {
        String query = "UPDATE condition_reports SET total_extra_cost = total_extra_cost + ? WHERE lease_id = ?";
        jdbcTemplate.update(query, damagePrice, leaseId);
    }
}
