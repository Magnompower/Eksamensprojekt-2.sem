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

@Controller
public class CustomersController {
    @Autowired
    CustomerService customerService;


    @GetMapping ("/customers")
    public String showCustomers(Model model) {
       model.addAttribute("customers", customerService.getAllCustomers());
        return "home/customers";
    }

    @PostMapping ("/delete_customer")
    public String deleteCustomer(@RequestParam  int customerId, Model model) {
        customerService.deleteCustomer(customerId);
        model.addAttribute("error", customerService.deleteCustomer(customerId));
        showCustomers(model);
        return "home/customers";
    }

    @GetMapping("/edit_customer")
    public String showEditCustomerForm(@RequestParam("id") int customerId, Model model) {
        Customer customer = customerService.getCustomerById(customerId);
        model.addAttribute("customer", customer);
        return "home/edit_customer";
    }

    @PostMapping("/edit_customer")
    public String editCustomerSubmit(@ModelAttribute Customer customer) {
        customerService.updateCustomer(customer);
        return "redirect:/customers";
    }
}

