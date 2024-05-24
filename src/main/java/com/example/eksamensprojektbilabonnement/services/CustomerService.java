package com.example.eksamensprojektbilabonnement.services;

import com.example.eksamensprojektbilabonnement.models.Customer;
import com.example.eksamensprojektbilabonnement.models.LeaseAgreement;
import com.example.eksamensprojektbilabonnement.repositories.CustomerRepository;
import com.example.eksamensprojektbilabonnement.repositories.LeaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    LeaseRepository leaseRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }

    public List<Customer> getNonAnonymousCustomers() {
        return customerRepository.getNonAnonymousCustomers();
    }

    public String deleteCustomer(int customerId) {
        //Checks if there are any non concluded leases for the customer:
        List<LeaseAgreement> nonConcludedLeases = leaseRepository.getNonConcludedLeases(customerId);

        //Tries to delete the customer from the database, if there are no non concluded leases.
        // If there are foreign key constraints, the data is anonymized instead
        if (nonConcludedLeases.isEmpty()) {
            try {
                customerRepository.deleteCustomer(customerId);
            } catch (Exception e) {
                if (e.getCause() instanceof SQLIntegrityConstraintViolationException) {
                    customerRepository.anonymizeCustomerData(customerId);
                    return "This customer has stored leases, and cannot be deleted. Customer data has been anonymized";
                }
            }
            return "Customer and all its data have been deleted.";
        } else {
            return "Customer has non concluded leases, and cannot be deleted";
        }
    }
}
