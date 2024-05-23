package com.example.eksamensprojektbilabonnement.services;

import com.example.eksamensprojektbilabonnement.models.Customer;
import com.example.eksamensprojektbilabonnement.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.getCustomers();
    }

    public String deleteCustomer(int customerId) {
        try {
            customerRepository.deleteCustomer(customerId);
        } catch (Exception e) {
            if (e.getCause() instanceof SQLIntegrityConstraintViolationException) {
                return "This customer has active leases and cannot be deleted";
            }

        } return "Customer and all its data have been deleted.";
    }
}
