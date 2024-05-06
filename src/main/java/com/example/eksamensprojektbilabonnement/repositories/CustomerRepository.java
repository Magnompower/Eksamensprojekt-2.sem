package com.example.eksamensprojektbilabonnement.repositories;

import com.example.eksamensprojektbilabonnement.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Customer> getCustomers () {
        String query = "SELECT * FROM customers";
        RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);

        return (List<Customer>) jdbcTemplate.queryForObject(query, rowMapper);
    }
}
