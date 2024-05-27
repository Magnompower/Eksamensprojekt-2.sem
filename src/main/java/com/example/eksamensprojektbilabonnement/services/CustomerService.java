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


/**
 * The Customer service.
 */
@Service
public class CustomerService {

    /**
     * The Customer repository.
     */
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private LeaseRepository leaseRepository;


    /**
     * Gets all customers.
     *
     * @return the all customers
     * @author Otto
     */
    public List<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }


    /**
     * Gets non-anonymous customers.
     * @author Hasan & Magne
     *
     * @return the non-anonymous customers
     */

    public List<Customer> getNonAnonymousCustomers() {
        return customerRepository.getNonAnonymousCustomers();
    }
    /**
     * Delete customer string.
     * @author Otto & Hasan
     *
     * @param customerId the customer id
     * @return the string
     */

    public String deleteCustomer(int customerId) {
        //Checks if there are any non concluded leases for the customer:
        List<LeaseAgreement> nonConcludedLeases = leaseRepository.getNonConcludedLeases(customerId);

        //Tries to delete the customer from the database, if there are no non concluded leases.
        // If there are foreign key constraints, the data is anonymized instead
        if (nonConcludedLeases.isEmpty()) {
            try {
                customerRepository.deleteCustomer(customerId);
                return "Customer and all its data have been deleted.";
            } catch (Exception e) {
                if (e.getCause() instanceof SQLIntegrityConstraintViolationException) {
                    customerRepository.anonymizeCustomerData(customerId);
                    return "This customer has stored leases, and cannot be deleted. Customer data has been anonymized";
                } else {
                    throw new RuntimeException("An unexpected error occurred while deleting the customer", e);
                }
            }
        } else {
            return "Customer has non concluded leases, and cannot be deleted";
        }
    }

    /**
     * Find customers for anonymization list.
     *
     * @return the list
     */
    public List<Integer> findCustomersForAnonymization() {
        return customerRepository.findCustomersForAnonymization();
    }

    public Customer getCustomerById(int customerId) {
        return customerRepository.getCustomerById(customerId);
    }

    public void updateCustomer(Customer customer) {
        customerRepository.updateCustomer(customer);
    }
}
