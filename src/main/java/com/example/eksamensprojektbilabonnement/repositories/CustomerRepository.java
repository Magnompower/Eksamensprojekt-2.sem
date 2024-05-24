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

    public List<Customer> getAllCustomers () {
        String query = "SELECT * FROM customers";
        RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class); //TODO: whats going on here
        return jdbcTemplate.query(query, rowMapper);
    }

    public void deleteCustomer(int customerId) {
        String query = "DELETE FROM customers WHERE customer_id = ?";
        jdbcTemplate.update(query, customerId);
    }

    public void anonymizeCustomerData(int customerId) {
        String query = "UPDATE customers SET " +
                "first_name = 'Anonymous'," +
                "last_name = 'Anonymous'," +
                "phone_number = 00000000," +
                "email = 'Anonymized'," +
                "address = 'Anonymized'" +
                "  WHERE customer_id = ?";
        jdbcTemplate.update(query, customerId);
    }

    public List<Customer> getNonAnonymousCustomers() {
        String query = "SELECT * FROM customers where first_name <> 'Anonymous'";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Customer.class));
    }

    public List<Integer> findCustomersForAnonymization() {
        String query = "SELECT l.customer_id " +
                "FROM lease_agreements l " +
                "WHERE l.end_date <= CURRENT_TIMESTAMP - INTERVAL 5 YEAR";
        return jdbcTemplate.query(query, (rs, rowNum) -> rs.getInt("customer_id"));
    }
}
