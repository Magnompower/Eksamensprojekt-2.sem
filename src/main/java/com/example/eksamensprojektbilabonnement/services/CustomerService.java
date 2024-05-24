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
        return customerRepository.getAllCustomers();
    }

    public String deleteCustomer(int customerId) {
        try {
            customerRepository.deleteCustomer(customerId);
        } catch (Exception e) {
            if (e.getCause() instanceof SQLIntegrityConstraintViolationException) {
                customerRepository.anonymizeCustomerData(customerId);
                return "This customer has active leases, and cannot be deleted. Customer data has been anonymized";
            }
        } return "Customer and all its data have been deleted.";
    }

    public List<Customer> getNonAnonymousCustomers() {
        return customerRepository.getNonAnonymousCustomers();
    }

    public List<Integer> findCustomersForAnonymization(){
        return customerRepository.findCustomersForAnonymization();
    }
}
