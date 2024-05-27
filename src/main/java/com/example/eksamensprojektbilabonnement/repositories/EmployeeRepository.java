package com.example.eksamensprojektbilabonnement.repositories;

import com.example.eksamensprojektbilabonnement.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


/**
 * The Employee repository.
 */
@Repository
public class EmployeeRepository {

    /**
     * The Jdbc template.
     */
    @Autowired
    JdbcTemplate jdbcTemplate;


    /**
     * Check pass string.
     *
     * @param email the email
     * @return the string
     * @author Hasan, Otto
     */
    public String checkPass(String email) {
        try {
            String query = "SELECT password FROM employees WHERE email = ?;";
            return jdbcTemplate.queryForObject(query, String.class, email);
        } catch (EmptyResultDataAccessException e) {
            return "UserNotFound";
        }
    }

    /**
     * Gets employee.
     *
     * @param email the email
     * @return the employee
     * @author Hasan
     */
    public Employee getEmployee(String email) {
        String query = "SELECT * from employees where email = ?";
        return jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(Employee.class), email);
    }
}
