package com.example.eksamensprojektbilabonnement.services;

import com.example.eksamensprojektbilabonnement.models.Customer;
import com.example.eksamensprojektbilabonnement.repositories.CustomerRepository;
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
    CustomerRepository customerRepository;

    /**
     * Gets all customers.
     * @author Otto
     *
     * @return the all customers
     */
    public List<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }

    /**
     * Delete customer string.
     * @author Otto & Hasan
     *
     * @param customerId the customer id
     * @return the string
     * @throws SQLIntegrityConstraintViolationException the sql integrity constraint violation exception
     */
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
     * Find customers for anonymization list.
     * @author Magne & Otto
     *
     * @return the list
     */
    public List<Integer> findCustomersForAnonymization(){
        return customerRepository.findCustomersForAnonymization();
    }
}
