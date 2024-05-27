package com.example.eksamensprojektbilabonnement.repositories;

import com.example.eksamensprojektbilabonnement.models.Damage;
import com.example.eksamensprojektbilabonnement.models.inheritance.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * The Damage repository.
 */
@Repository
public class DamageRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Add damage to table.
     *
     * @param chassisNumber the chassis number
     * @param damageName    the damage name
     * @param damagePrice   the damage price
     * @param leaseId       the lease id
     * @author Anders
     */
    public void addNonInvoicedDamage(String chassisNumber, String damageName, double damagePrice, int leaseId) {
        String query = "INSERT INTO damages (chassis_number, damage_name, damage_price, lease_id)"
                + "VALUES(?,?,?,?)";
        jdbcTemplate.update(query, chassisNumber, damageName, damagePrice,leaseId);
    }

    /**
     * Gets damages from table.
     *
     * @param chassisNumber the chassis number
     * @return the damages from table
     * @author Hasan
     */
    public List<Damage> getDamagesFromTable(String chassisNumber) {
        String query = "SELECT * FROM damages WHERE chassis_number = ?";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Damage.class), chassisNumber);
    }

    /**
     * Gets invoiced damages.
     *
     * @param chassisNumber the chassis number
     * @return the invoiced damages
     * @author Otto
     */
    public List<Damage> getInvoicedDamages(String chassisNumber) {
        String query = "SELECT * FROM damages WHERE chassis_number = ? AND invoiced = TRUE;";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Damage.class), chassisNumber);
    }

    /**
     * Gets non invoiced damages.
     *
     * @param chassisNumber the chassis number
     * @param leaseId       the lease id
     * @return the non invoiced damages
     * @author Hasan
     */
    public List<Damage> getNonInvoicedDamages(String chassisNumber, int leaseId) {
        String query = "SELECT * FROM damages WHERE chassis_number = ? AND lease_id = ? AND invoiced = FALSE";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Damage.class), chassisNumber, leaseId);
    }

    /**
     * Sets damages to invoice.
     *
     * @param leaseId       the lease id
     * @param chassisNumber the chassis number
     * @author Hasan
     */
    public void setDamagesToInvoiced(int leaseId, String chassisNumber) {
        String query = "UPDATE damages SET invoiced = TRUE WHERE lease_id = ? AND chassis_number = ? ";
        jdbcTemplate.update(query, leaseId, chassisNumber);
    }

    /**
     * Update car state.
     *
     * @param chassisNumber the chassis number
     * @param carState      the car state
     * @param carTable      the car table
     * @author Anders
     */
    public void updateCarState(String chassisNumber, String carState, String carTable) {
        String query = "UPDATE " + carTable + " SET car_state = ? WHERE chassis_number = ?";
        jdbcTemplate.update(query, carState, chassisNumber);
    }

    /**
     * Add damage.
     *
     * @param chassisNumber the chassis number
     * @param damageName    the damage name
     * @param damagePrice   the damage price
     * @author Hasan
     */
    public void addDamage(String chassisNumber, String damageName, double damagePrice) {
        String query = "INSERT INTO damages (chassis_number, damage_name, damage_price, invoiced)"
                + "VALUES(?,?,?,?)";
        jdbcTemplate.update(query, chassisNumber, damageName, damagePrice, 1);
    }

    /**
     * Delete damage.
     *
     * @param damageId the damage id
     * @author Hasan
     */
    public void deleteDamage(int damageId) {
        String query = "DELETE FROM damages WHERE damage_id = ?";
        jdbcTemplate.update(query,damageId);
    }
}

