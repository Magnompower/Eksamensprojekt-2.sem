package com.example.eksamensprojektbilabonnement.repositories;


import com.example.eksamensprojektbilabonnement.models.LeaseAgreement;
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


/**
 * The Inventory repository.
 */
@Repository
public class InventoryRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Gets all cars.
     * @author Anders
     *
     * @return the all cars
     */
    public List<Car> getAllCars() {
        String query = "SELECT * FROM all_cars_view";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Car.class));
    }

    /**
     * Gets sorted cars.
     * @author Otto
     *
     * @param sortByColumn  the sort by column
     * @param sortDirection the sort direction
     * @return the sorted cars
     */
    public List<Car> getSortedCars(String sortByColumn, String sortDirection) {
        String query = "SELECT * FROM all_cars_view ORDER BY " + sortByColumn + " " + sortDirection;
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Car.class));
    }

    /**
     * Gets filtered cars.
     * @author Hasan
     *
     * @param filterBy the filter by
     * @return the filtered cars
     */
    public List<Car> getFilteredCars(String filterBy) {
        String query = "SELECT * FROM all_cars_view WHERE car_state = ?";
        return jdbcTemplate.query(query, new Object[]{filterBy}, BeanPropertyRowMapper.newInstance(Car.class));
    }

    /**
     * Gets sorted and filtered cars.
     * @author Hasan
     *
     * @param filterBy      the filter by
     * @param sortByColumn  the sort by column
     * @param sortDirection the sort direction
     * @return the sorted and filtered cars
     */
    public List<Car> getSortedAndFilteredCars(String filterBy, String sortByColumn, String sortDirection) {
        String query = "SELECT * FROM all_cars_view WHERE car_state = ? ORDER BY " + sortByColumn + " " + sortDirection;
        return jdbcTemplate.query(query, new Object[]{filterBy},BeanPropertyRowMapper.newInstance(Car.class));
        }

}



