package com.example.eksamensprojektbilabonnement.controllers;

import com.example.eksamensprojektbilabonnement.models.inheritance.Car;
import com.example.eksamensprojektbilabonnement.services.CarService;
import com.example.eksamensprojektbilabonnement.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/view_car")
    public String view_car(@RequestParam String carChassisNumber, Model model) {
        Car car = carService.getCarByChassisNumber(carChassisNumber);
        model.addAttribute("Car", car);
        model.addAttribute("Customers", customerService.getAllCustomers());
        return "home/view_car";
    }

    @PostMapping("/update_car")
    public String updateCar(@RequestParam String chassisNumber, @RequestParam String carState) {
        String carTable = carService.getCarTable(chassisNumber);
        carService.updateCarState(chassisNumber, carState, carTable);
        return "redirect:/inventory";
    }

    @GetMapping("/car_bought")
    public String carBought(Model model){

        return "home/car_bought";
    }

    @GetMapping("/car_returned")
    public String carReturned(Model model){

        return "home/car_returned";
    }

}
