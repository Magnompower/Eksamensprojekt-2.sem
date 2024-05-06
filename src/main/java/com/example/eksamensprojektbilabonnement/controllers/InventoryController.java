package com.example.eksamensprojektbilabonnement.controllers;

import com.example.eksamensprojektbilabonnement.models.inheritance.GasCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.eksamensprojektbilabonnement.models.inheritance.Car;
import com.example.eksamensprojektbilabonnement.services.InventoryService;

import java.util.List;

@Controller
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/inventory")
    public String showInventory(Model model) {
        List<GasCar> gasCars = inventoryService.getAllGasCars(); // Assuming you have a method to retrieve all gas cars
        model.addAttribute("GasCar", gasCars);
        return "home/inventory"; // The name of your HTML template
    }
}