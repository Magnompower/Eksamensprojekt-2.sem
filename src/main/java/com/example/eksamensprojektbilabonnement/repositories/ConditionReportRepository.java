package com.example.eksamensprojektbilabonnement.repositories;

import com.example.eksamensprojektbilabonnement.models.ConditionReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ConditionReportRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public void createConditionReport(String chassisNumber, int leaseId, double kmBeforeLease) {
        String query = "INSERT INTO condition_reports (chassis_number, lease_id, km_before_lease)" +
                "VALUES (?,?,?)";
        jdbcTemplate.update(query, chassisNumber, leaseId, kmBeforeLease);
    }


    public ConditionReport getConditionReport(int leaseId) {
        String query = "SELECT * FROM condition_reports WHERE lease_id = ?";
        return jdbcTemplate.queryForObject(query,BeanPropertyRowMapper.newInstance(ConditionReport.class), leaseId);
    }

    public void setKmDrivenAfterLease(int leaseId, double kmDriven) {
        String query = "UPDATE condition_reports SET km_after_lease = ? WHERE lease_id = ?";
        jdbcTemplate.update(query, leaseId, kmDriven);


    }
}
