package com.example.eksamensprojektbilabonnement.controllers;

import com.example.eksamensprojektbilabonnement.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
     * @author Otto
     *
     * @param model the model
     * @return the string
     */
    @GetMapping ("/customers")
    public String showCustomers(Model model) {
       model.addAttribute("customers", customerService.getAllCustomers());
        return "home/customers";
    }

    /**
     * Delete customer string.
     * @author Otto, Hasan
     *
     * @param customerId the customer id
     * @param model      the model
     * @return the string
     */
    @PostMapping ("/delete_customer")
    public String deleteCustomer(@RequestParam  int customerId, Model model) {
        customerService.deleteCustomer(customerId);
        model.addAttribute("error", customerService.deleteCustomer(customerId));
        showCustomers(model);
        return "home/customers";
    }
}

