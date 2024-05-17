package com.example.eksamensprojektbilabonnement.repositories;


import com.example.eksamensprojektbilabonnement.models.inheritance.Car;
import com.example.eksamensprojektbilabonnement.models.inheritance.GasCar;
import com.example.eksamensprojektbilabonnement.utilities.CarState;
import com.example.eksamensprojektbilabonnement.utilities.FuelType;
import com.example.eksamensprojektbilabonnement.utilities.TransmissionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CarRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String getAllCarsChassisNumber() {
        String query = "SELECT * from all_cars";
        RowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);
        return jdbcTemplate.query(query, rowMapper).toString();
    }

    public void createCar(String carChassisNumber, String carModel, String brand, double price, double registrationFee, double kmPerLiter, double carbonEmissionPerKm, String licensePlate, CarState carState, TransmissionType transmissionType, FuelType fuelType, String image_url) {
        String query = "INSERT INTO gas_cars (chassis_number, license_plate_number, brand, model, registration_fee, price, car_state, transmission_type, km_per_liter, fuel_type, carbon_emission_per_km, image_url) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(query, carChassisNumber, licensePlate, brand, carModel, registrationFee, price, carState.name(), transmissionType.name(), kmPerLiter, fuelType.name(), carbonEmissionPerKm, image_url);
    }

    public Car getCarByChassisNumber(String carChassisNumber) {
        String query = "SELECT * FROM all_cars_view WHERE chassis_number = ?";
        return jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(Car.class), carChassisNumber);
    }

    public String getCarTable(String chassisNumber) {
        String query = "SELECT car_table FROM all_cars WHERE chassis_number = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{chassisNumber}, String.class);
    }

    public void updateCarState(String chassisNumber, String CarState, String carTable) {
        String query = "UPDATE " + carTable + " SET car_state = ? WHERE chassis_number = ?";
        jdbcTemplate.update(query, CarState, chassisNumber);
    }




}

