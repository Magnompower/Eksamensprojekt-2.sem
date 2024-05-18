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

    public void addDamageToTable(String chassisNumber, String damage, double price) {
        String query = "INSERT INTO damages (chassis_number, damage_name, damage_price)"
                + "VALUES(?,?,?)";
        jdbcTemplate.update(query, chassisNumber, damage, price);
    }

    public List<Damage> getDamagesFromTable(String chassisNumber) {
        String query = "SELECT * FROM damages WHERE chassis_number = ?";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Damage.class), chassisNumber);
    }

    public List<Car> getFilteredCars(String filterBy) {
        String query = "SELECT * FROM all_cars_view WHERE car_state = ?";
        return jdbcTemplate.query(query, new Object[]{filterBy}, BeanPropertyRowMapper.newInstance(Car.class));
    }

    public Car getCarByChassisNumber(String carChassisNumber) {
        String query = "SELECT * FROM all_cars_view WHERE chassis_number = ?";
        return jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(Car.class), carChassisNumber);
    }

}

