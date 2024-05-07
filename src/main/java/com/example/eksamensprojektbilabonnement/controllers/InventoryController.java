package com.example.eksamensprojektbilabonnement.controllers;

import com.example.eksamensprojektbilabonnement.models.inheritance.GasCar;
import com.example.eksamensprojektbilabonnement.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.eksamensprojektbilabonnement.models.inheritance.Car;
import com.example.eksamensprojektbilabonnement.services.InventoryService;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private CustomerService customerService;

    @GetMapping("/inventory")
    public String showInventory(Model model) {
        List<GasCar> gasCars = inventoryService.getAllGasCars(); // Assuming you have a method to retrieve all gas cars
        model.addAttribute("GasCar", gasCars);
        return "home/inventory"; // The name of your HTML template
    }
    @GetMapping ("/view_car")
    public String view_car(@RequestParam String carChassisNumber, Model model) {
        Car car = inventoryService.getCarByChassisNumber(carChassisNumber);
        model.addAttribute("Car", car);

        model.addAttribute("Customers", customerService.getAllCustomers());
        return "home/view_car";
    }
}
