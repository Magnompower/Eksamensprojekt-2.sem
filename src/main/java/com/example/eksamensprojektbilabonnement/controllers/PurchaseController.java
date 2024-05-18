package com.example.eksamensprojektbilabonnement.controllers;

import com.example.eksamensprojektbilabonnement.services.CarService;
import com.example.eksamensprojektbilabonnement.utilities.CarState;
import com.example.eksamensprojektbilabonnement.utilities.FuelType;
import com.example.eksamensprojektbilabonnement.utilities.TransmissionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PurchaseController {
    @Autowired
    CarService carService;


    @GetMapping("/purchase")
    public String showRandomCars( Model model) {
      model.addAttribute("cars",  carService.generateRandomCars(5));
        return "home/purchase";
    }
    @PostMapping("/purchaseSelected")
    public String purchaseSelected(Model model, @RequestParam String carChassisNumber, @RequestParam String carModel, @RequestParam String brand, @RequestParam double price, @RequestParam double registrationFee, @RequestParam double kmPerLiter, @RequestParam double carbonEmissionPerKm, @RequestParam String licensePlate, @RequestParam String image_url, @RequestParam CarState carState, @RequestParam TransmissionType transmissionType, @RequestParam FuelType fuelType) {
        carService.createCar(carChassisNumber, carModel, brand, price, registrationFee, kmPerLiter, carbonEmissionPerKm, licensePlate, carState, transmissionType, fuelType, image_url);
        return "redirect:/inventory";
    }

}
