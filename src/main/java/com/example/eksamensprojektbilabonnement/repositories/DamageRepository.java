package com.example.eksamensprojektbilabonnement.repositories;

import com.example.eksamensprojektbilabonnement.models.Damage;
import com.example.eksamensprojektbilabonnement.models.inheritance.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DamageRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addDamageToTable(String chassisNumber, String damageName, double damagePrice, int leaseId) {
        String query = "INSERT INTO damages (chassis_number, damage_name, damage_price, lease_id)"
                + "VALUES(?,?,?,?)";
        jdbcTemplate.update(query, chassisNumber, damageName, damagePrice,leaseId);
    }

    public List<Damage> getDamagesFromTable(String chassisNumber) {
        String query = "SELECT * FROM damages WHERE chassis_number = ?";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Damage.class), chassisNumber);
    }

    public List<Damage> getInvoicedDamages(String chassisNumber) {
        String query = "SELECT * FROM damages WHERE chassis_number = ? AND invoiced = TRUE;";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Damage.class), chassisNumber);
    }

    public List<Damage> getNonInvoicedDamages(String chassisNumber, int leaseId) {
        String query = "SELECT * FROM damages WHERE chassis_number = ? AND lease_id = ? AND invoiced = FALSE";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Damage.class), chassisNumber, leaseId);
    }

    public void setDamagesToInvoiced(int leaseId, String chassisNumber) {
        String query = "UPDATE damages SET invoiced = TRUE WHERE lease_id = ? AND chassis_number = ? ";
        jdbcTemplate.update(query, leaseId, chassisNumber);
    }

    public void updateCarState(String chassisNumber, String carState, String carTable) {
        String query = "UPDATE " + carTable + " SET car_state = ? WHERE chassis_number = ?";
        jdbcTemplate.update(query, carState, chassisNumber);
    }
}

