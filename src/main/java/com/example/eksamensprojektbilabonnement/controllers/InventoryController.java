package com.example.eksamensprojektbilabonnement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.eksamensprojektbilabonnement.models.inheritance.Car;
import com.example.eksamensprojektbilabonnement.services.InventoryService;

@Controller
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/inventory")
    public String inventoryDetails(Model model) {
        Car car = inventoryService.getCarById(1);

        model.addAttribute("car", car);

        return "inventory_details";
    }
}
