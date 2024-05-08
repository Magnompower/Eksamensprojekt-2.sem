package com.example.eksamensprojektbilabonnement.repositories;


import com.example.eksamensprojektbilabonnement.models.inheritance.Car;
import com.example.eksamensprojektbilabonnement.models.inheritance.ElectricCar;
import com.example.eksamensprojektbilabonnement.models.inheritance.GasCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;

@Repository
public class InventoryRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public GasCar getCarByLicensePlateNumber(String licensePlateNumber) {
        String query = "SELECT * FROM gas_cars WHERE license_plate_number = ?";
        try {
            return jdbcTemplate.queryForObject(query, new Object[]{licensePlateNumber}, (rs, rowNum) -> {
                GasCar car = new GasCar();
                car.setLicensePlateNumber(rs.getString("license_plate_number"));
                car.setModel(rs.getString("brand"));
                //TODO add more fields
                return car;
            });
        } catch (EmptyResultDataAccessException e) {
            // Handle case where no car is found with the given license plate number
            return null;
        }
    }

    public GasCar getCarByChassisNumber(String carChassisNumber) { //vi skal tage højde for de forskellige typer biler
        String query = "SELECT * FROM all_cars_view WHERE chassis_number = ?";
        return jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(GasCar.class), carChassisNumber);
    }

    public List<GasCar> getAllGasCars() {
        String query = "SELECT * FROM gas_cars";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(GasCar.class));
    }

    public List<Car> getAllCars() {
        String query = "SELECT * FROM all_cars_view";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Car.class));
    }

    public List<Car> getSortedCars(String sortByColumn, String sortDirection) {
        String query = "SELECT * FROM all_cars_view ORDER BY " + sortByColumn + " " + sortDirection;
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Car.class));
    }

    public List<Car> getSortedAndFilteredCars(String filterBy, String sortByColumn, String sortDirection) {
        String query = "SELECT * FROM all_cars_view WHERE car_state = ? ORDER BY " + sortByColumn + " " + sortDirection;
        return jdbcTemplate.query(query, new Object[]{filterBy},BeanPropertyRowMapper.newInstance(Car.class));
        }


}




