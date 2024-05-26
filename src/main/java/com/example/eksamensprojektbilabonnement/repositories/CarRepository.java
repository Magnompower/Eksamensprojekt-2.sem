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

/**
 * The Car repository.
 */
@Repository
public class CarRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Create car.
     * @author Hasan, Otto
     *
     * @param chassisNumber       the chassis number
     * @param carModel            the car model
     * @param brand               the brand
     * @param price               the price
     * @param registrationFee     the registration fee
     * @param kmPerLiter          the km per liter
     * @param carbonEmissionPerKm the carbon emission per km
     * @param licensePlate        the license plate
     * @param carState            the car state
     * @param transmissionType    the transmission type
     * @param fuelType            the fuel type
     * @param image_url           the image url
     */
    public void createCar(String chassisNumber, String carModel, String brand, double price, double registrationFee, double kmPerLiter, double carbonEmissionPerKm, String licensePlate, CarState carState, TransmissionType transmissionType, FuelType fuelType, String image_url) {
        String query = "INSERT INTO gas_cars (chassis_number, license_plate_number, brand, model, registration_fee, price, car_state, transmission_type, km_per_liter, fuel_type, carbon_emission_per_km, image_url) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(query, chassisNumber, licensePlate, brand, carModel, registrationFee, price, carState.name(), transmissionType.name(), kmPerLiter, fuelType.name(), carbonEmissionPerKm, image_url);
    }

    /**
     * Gets car by chassis number.
     * @author Hasan
     *
     * @param chassisNumber the chassis number
     * @return the car by chassis number
     */
    public Car getCarByChassisNumber(String chassisNumber) {
        String query = "SELECT * FROM all_cars_view WHERE chassis_number = ?";
        return jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(Car.class), chassisNumber);
    }

    /**
     * Gets car table.
     * @author Hasan, Anders
     *
     * @param chassisNumber the chassis number
     * @return the car table
     */
    public String getCarTable(String chassisNumber) {
        String query = "SELECT car_table FROM all_cars WHERE chassis_number = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{chassisNumber}, String.class);
    }

    /**
     * Update car state.
     * @author Magne
     *
     * @param chassisNumber the chassis number
     * @param carState      the car state
     * @param carTable      the car table
     */
    public void updateCarState(String chassisNumber, String carState, String carTable) {
        String query = "UPDATE " + carTable + " SET car_state = ? WHERE chassis_number = ?";
        jdbcTemplate.update(query, carState, chassisNumber);
    }


    /**
     * Update km driven.
     * @author Hasan
     *
     * @param chassisNumber the chassis number
     * @param kmDriven      the km driven
     * @param carTable      the car table
     */
    public void updateKmDriven(String chassisNumber, double kmDriven, String carTable) {
        String query = "UPDATE " + carTable + " SET km_driven = ? WHERE chassis_number = ?";
        jdbcTemplate.update(query, kmDriven, chassisNumber);
    }

    /**
     * Change car state in leased cars.
     * @author Magne
     *
     * @param chassisNumber the chassis number
     * @param carState      the car state
     */
    public void changeCarStateInLeasedCars(String chassisNumber, String carState) {
        String query = "UPDATE " + getCarTable(chassisNumber) + " SET car_state = ? WHERE chassis_number = ?";
        jdbcTemplate.update(query, carState, chassisNumber);
    }


    /**
     * Find cars with upcoming leases list.
     * @author Magne
     *
     * @return the list
     */
    public List<String> findCarsWithUpcomingLeases() {
        String query = "SELECT a.chassis_number FROM lease_agreements l JOIN all_cars a ON " +
                "l.chassis_number = a.chassis_number WHERE l.start_date BETWEEN CURRENT_TIMESTAMP " +
                "AND (CURRENT_TIMESTAMP + INTERVAL 24 HOUR)";
        return jdbcTemplate.queryForList(query, String.class);
    }


}

