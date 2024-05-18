package com.example.eksamensprojektbilabonnement.controllers;

import com.example.eksamensprojektbilabonnement.models.Damage;
import com.example.eksamensprojektbilabonnement.models.inheritance.Car;
import com.example.eksamensprojektbilabonnement.services.CarService;
import com.example.eksamensprojektbilabonnement.services.CustomerService;
import com.example.eksamensprojektbilabonnement.services.DamageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private DamageService damageService;

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
    public String carBought(Model model, @RequestParam String chassisNumber){
        List<Damage> damages = damageService.getDamagesFromTable(chassisNumber);
        model.addAttribute("damages", damages);
        //Skal bruge forhåndsaftalen
        return "home/car_bought";
    }

    @GetMapping("/car_returned")
    public String carReturned(Model model, @RequestParam String chassisNumber){
        List<Damage> damages = damageService.getDamagesFromTable(chassisNumber);
        model.addAttribute("damages", damages);
        //Skal bruge lejeaftalen
        return "home/car_returned";
    }

}
