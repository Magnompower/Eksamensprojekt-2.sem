package com.example.eksamensprojektbilabonnement.controllers;

import com.example.eksamensprojektbilabonnement.models.Customer;
import com.example.eksamensprojektbilabonnement.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The Customers controller.
 */
@Controller
public class CustomersController {

    /**
     * The Customer service.
     */
    @Autowired
    CustomerService customerService;


    /**
     * Show customers string.
     *
     * @param model the model
     * @return the string
     * @author Otto
     */
    @GetMapping ("/customers")
    public String showCustomers(Model model) {
       model.addAttribute("customers", customerService.getNonAnonymousCustomers());
        return "home/lease_registration/customers";
    }

    /**
     * Delete customer string.
     *
     * @param customerId the customer id
     * @param model      the model
     * @return the string
     * @author Otto, Hasan
     */
    @PostMapping ("/delete_customer")
    public String deleteCustomer(@RequestParam  int customerId, Model model) {
        customerService.deleteCustomer(customerId);
        model.addAttribute("error", customerService.deleteCustomer(customerId));
        showCustomers(model);
        return "home/lease_registration/customers";
    }

    /**
     * Show edit customer form string.
     *
     * @param customerId the customer id
     * @param model      the model
     * @return the string
     * @author Magne
     */
    @GetMapping("/edit_customer")
    public String showEditCustomerForm(@RequestParam int customerId, Model model) {
        Customer customer = customerService.getCustomerById(customerId);
        model.addAttribute("customer", customer);
        return "home/lease_registration/edit_customer";
    }

    /**
     * Edit customer submit string.
     *
     * @param customer the customer
     * @return the string
     * @author Magne
     */
    @PostMapping("/edit_customer")
    public String editCustomerSubmit(@ModelAttribute Customer customer) {
        customerService.updateCustomer(customer);
        return "redirect:/customers";
    }
}

