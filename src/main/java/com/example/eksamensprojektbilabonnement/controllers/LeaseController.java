package com.example.eksamensprojektbilabonnement.controllers;

import com.example.eksamensprojektbilabonnement.services.CarService;
import com.example.eksamensprojektbilabonnement.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LeaseController {
    @Autowired
    private CarService carService;
    @Autowired
    private CustomerService customerService;

    //todo create controller that sends  a car and customer over so that a lease can be created
    @GetMapping("/createlease")
    public String createLease(Model model) {
        model.addAttribute("Cars" , carService.getAllCarsChassisNumber());
        model.addAttribute("Customers", customerService.getAllCustomers());
        return "createlease";
    }
}
