package com.example.eksamensprojektbilabonnement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.eksamensprojektbilabonnement.models.inheritance.Car;
import com.example.eksamensprojektbilabonnement.services.CarService;

@Controller
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("/car/{id}")
    public String carDetails(@PathVariable("id") int id, Model model) {
        Car car = carService.getCarById(id);

        model.addAttribute("car", car);

        return "car_details";
    }
}
