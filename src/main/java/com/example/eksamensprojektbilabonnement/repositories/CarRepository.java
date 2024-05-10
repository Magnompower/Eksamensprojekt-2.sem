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

    public Car getCarById(int id) {
        String query = "SELECT * FROM cars WHERE id = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{id}, (rs, rowNum) -> {
            GasCar car = new GasCar();
            car.setModel(rs.getString("name"));
            car.setImage_url(rs.getString("image"));
            return car;
        });
    }


    public String getAllCarsChassisNumber() {
        String query = "SELECT * from all_cars";
        RowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);
        return jdbcTemplate.query(query, rowMapper).toString();
    }

    public void createCar(String carChassisNumber, String carModel, String brand, double price, double registrationFee, double kmPerLiter, double carbonEmissionPerKm, String licensePlate, CarState carState, TransmissionType transmissionType, FuelType fuelType, String image_url) {
        String query = "INSERT INTO gas_cars (chassis_number, license_plate_number, brand, model, registration_fee, price, car_state, transmission_type, km_per_liter, fuel_type, carbon_emission_per_km, image_url ) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            jdbcTemplate.update(query, carChassisNumber, licensePlate, brand, carModel, registrationFee, price, carState.name(), transmissionType.name(), kmPerLiter, fuelType.name(), carbonEmissionPerKm, image_url);


    }
}

