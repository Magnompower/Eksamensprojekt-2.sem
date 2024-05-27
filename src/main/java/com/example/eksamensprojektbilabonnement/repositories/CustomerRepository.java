package com.example.eksamensprojektbilabonnement.repositories;

import com.example.eksamensprojektbilabonnement.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * The Customer repository.
 */
@Repository
public class CustomerRepository {

    /**
     * The Jdbc template.
     */
    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * Gets all customers.
     *
     * @return the all customers
     * @author Hasan, Otto
     */
    public List<Customer> getAllCustomers () {
        String query = "SELECT * FROM customers";
        RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class); //TODO: whats going on here
        return jdbcTemplate.query(query, rowMapper);
    }

    /**
     * Delete customer.
     *
     * @param customerId the customer id
     * @author Otto
     */
    public void deleteCustomer(int customerId) {
        String query = "DELETE FROM customers WHERE customer_id = ?";
        jdbcTemplate.update(query, customerId);
    }

    /**
     * Anonymize customer data.
     *
     * @param customerId the customer id
     * @author Hasan
     */
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

    /**
     * Gets non-anonymous customers.
     *
     * @return the non-anonymous customers
     * @author Hasan
     */
    public List<Customer> getNonAnonymousCustomers() {
        String query = "SELECT * FROM customers where first_name <> 'Anonymous'";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Customer.class));
    }

    /**
     * Find customers for anonymization list.
     *
     * @return the list
     * @author Magne
     */
    public List<Integer> findCustomersForAnonymization() {
        String query = "SELECT l.customer_id " +
                "FROM lease_agreements l " +
                "WHERE l.end_date <= CURRENT_TIMESTAMP - INTERVAL 5 YEAR";
        return jdbcTemplate.query(query, (rs, rowNum) -> rs.getInt("customer_id"));
    }

    /**
     * Gets customer by id.
     *
     * @param customerId the customer id
     * @return the customer by id
     * @author Anders
     */
    public Customer getCustomerById(int customerId) {
        String query = "SELECT * FROM customers WHERE customer_id = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{customerId}, BeanPropertyRowMapper.newInstance(Customer.class));
    }

    /**
     * Update customer.
     *
     * @param customer the customer
     * @author Anders
     */
    public void updateCustomer(Customer customer) {
        String query = "UPDATE customers SET first_name = ?, last_name = ?, phone_number = ?, email = ?, address = ? WHERE customer_id = ?";
        jdbcTemplate.update(query, customer.getFirstName(), customer.getLastName(), customer.getPhoneNumber(), customer.getEmail(), customer.getAddress(), customer.getCustomerId());
    }
}
