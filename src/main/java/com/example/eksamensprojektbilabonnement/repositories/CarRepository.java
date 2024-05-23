package com.example.eksamensprojektbilabonnement.repositories;


import com.example.eksamensprojektbilabonnement.models.inheritance.Car;
import com.example.eksamensprojektbilabonnement.utilities.CarState;
import com.example.eksamensprojektbilabonnement.utilities.FuelType;
import com.example.eksamensprojektbilabonnement.utilities.TransmissionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void createCar(String chassisNumber, String carModel, String brand, double price, double registrationFee, double kmPerLiter, double carbonEmissionPerKm, String licensePlate, CarState carState, TransmissionType transmissionType, FuelType fuelType, String image_url) {
        String query = "INSERT INTO gas_cars (chassis_number, license_plate_number, brand, model, registration_fee, price, car_state, transmission_type, km_per_liter, fuel_type, carbon_emission_per_km, image_url) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(query, chassisNumber, licensePlate, brand, carModel, registrationFee, price, carState.name(), transmissionType.name(), kmPerLiter, fuelType.name(), carbonEmissionPerKm, image_url);
    }

    public Car getCarByChassisNumber(String chassisNumber) {
        String query = "SELECT * FROM all_cars_view WHERE chassis_number = ?";
        return jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(Car.class), chassisNumber);
    }

    public String getCarTable(String chassisNumber) {
        String query = "SELECT car_table FROM all_cars WHERE chassis_number = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{chassisNumber}, String.class);
    }

    public void updateCarState(String chassisNumber, String carState, String carTable) {
        String query = "UPDATE " + carTable + " SET car_state = ? WHERE chassis_number = ?";
        jdbcTemplate.update(query, carState, chassisNumber);
    }


    public void updateKmDriven(String chassisNumber, double kmDriven, String carTable) {
        String query = "UPDATE " + carTable + " SET km_driven = ? WHERE chassis_number = ?";
        jdbcTemplate.update(query, kmDriven, chassisNumber);
    }

    public void changeCarStateInLeasedCars(String chassisNumber, String carState) {
        String query = "UPDATE " + getCarTable(chassisNumber) + " SET car_state = ? WHERE chassis_number = ?";
        jdbcTemplate.update(query, carState, chassisNumber);
    }



    public List<String> findCarsWithUpcomingLeases() {
        String query = "SELECT a.chassis_number FROM lease_agreements l JOIN all_cars a ON " +
                "l.chassis_number = a.chassis_number WHERE l.start_date BETWEEN CURRENT_TIMESTAMP " +
                "AND (CURRENT_TIMESTAMP + INTERVAL 24 HOUR)";
        return jdbcTemplate.queryForList(query, String.class);
    }


}

