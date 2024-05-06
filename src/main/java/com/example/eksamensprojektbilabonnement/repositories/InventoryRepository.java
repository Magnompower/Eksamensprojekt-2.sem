package com.example.eksamensprojektbilabonnement.repositories;


import com.example.eksamensprojektbilabonnement.models.inheritance.Car;
import com.example.eksamensprojektbilabonnement.models.inheritance.GasCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
    public List<GasCar> getAllGasCars() {
        String query = "SELECT * FROM gas_cars";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(GasCar.class));
    }
}


/*
public void addProduct(String name, String url, String description, double price, int amount, int wishlistId) {
        String sql = "INSERT INTO products (wishlist_id, name, url, description, price, amount) VALUES (?, ?, ?, ?, ?, ?);";
        jdbcTemplate.update(sql, wishlistId, name, url, description, price, amount);


public Product getProduct(int productId) {
        String query = "SELECT * FROM products WHERE product_id = ?;";
        RowMapper<Product> rowMapper = new BeanPropertyRowMapper<>(Product.class);
        return jdbcTemplate.queryForObject(query, rowMapper, productId);

    }

 */