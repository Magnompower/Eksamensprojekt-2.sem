package com.example.eksamensprojektbilabonnement.repositories;


import com.example.eksamensprojektbilabonnement.models.inheritance.Car;
import com.example.eksamensprojektbilabonnement.models.inheritance.GasCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CarRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Car getCarById(int id) {
        String query = "SELECT * FROM cars WHERE id = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{id}, (rs, rowNum) -> {
            GasCar car = new GasCar();
            car.setVehicleId(rs.getInt("id"));
            car.setModel(rs.getString("name"));
            car.setImage(rs.getBytes("image"));
            return car;
        });
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