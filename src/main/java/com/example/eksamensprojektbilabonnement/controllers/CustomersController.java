package com.example.eksamensprojektbilabonnement.controllers;

import com.example.eksamensprojektbilabonnement.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CustomersController {
    @Autowired
    CustomerService customerService;


    @GetMapping ("/customers")
    public String showCustomers(Model model) {
       model.addAttribute("customers", customerService.getAllCustomers());
        return "home/lease_registration/customers";
    }
    @PostMapping ("/delete_customer")
    public String deleteCustomer(@RequestParam  int customerId, Model model) {
        customerService.deleteCustomer(customerId);
        model.addAttribute("error", customerService.deleteCustomer(customerId));
        showCustomers(model);
        return "home/lease_registration/customers";
    }
}

