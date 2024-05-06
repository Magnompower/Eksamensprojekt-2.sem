package com.example.eksamensprojektbilabonnement.services;

import com.example.eksamensprojektbilabonnement.models.Customer;
import com.example.eksamensprojektbilabonnement.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
    return customerRepository.getCustomers();
    }
}
