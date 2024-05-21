package com.example.eksamensprojektbilabonnement.controllers;

import com.example.eksamensprojektbilabonnement.models.Damage;
import com.example.eksamensprojektbilabonnement.models.LeaseAgreement;
import com.example.eksamensprojektbilabonnement.models.inheritance.Car;
import com.example.eksamensprojektbilabonnement.services.CarService;
import com.example.eksamensprojektbilabonnement.services.CustomerService;
import com.example.eksamensprojektbilabonnement.services.DamageService;
import com.example.eksamensprojektbilabonnement.services.LeaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private DamageService damageService;

    @Autowired
    private LeaseService leaseService;

    @GetMapping("/view_car")
    public String view_car(@RequestParam String chassisNumber, Model model) {
        Car car = carService.getCarByChassisNumber(chassisNumber);
        model.addAttribute("Car", car);
        model.addAttribute("Customers", customerService.getAllCustomers());
        return "home/view_car";
    }

    @PostMapping("/update_car_state")
    public String updateCar(@RequestParam String chassisNumber, @RequestParam String carState) {
        String carTable = carService.getCarTable(chassisNumber);
        carService.updateCarState(chassisNumber, carState, carTable);
        return "redirect:/inventory";
    }

    @PostMapping("/update_km_driven")
    public String updateKmDriven(@RequestParam String chassisNumber, @RequestParam double kmDriven, RedirectAttributes redirectAttributes) {
        String carTable = carService.getCarTable(chassisNumber);
        carService.updateKmDriven(chassisNumber, kmDriven, carTable);
        redirectAttributes.addAttribute("chassisNumber", chassisNumber);
        return "redirect:/car_returned";
    }

    @GetMapping("/car_returned")
    public String carReturned(Model model, @RequestParam String chassisNumber){
        List<Damage> damages = damageService.getDamagesFromTable(chassisNumber);
        model.addAttribute("damages", damages);

        Car car = carService.getCarByChassisNumber(chassisNumber);
        model.addAttribute("car", car);

        LeaseAgreement lease = leaseService.getActiveLease(chassisNumber);
        model.addAttribute("lease", lease);

        return "home/car_returned";
    }
    @GetMapping("/car_bought")
    public String carBought(Model model, @RequestParam String chassisNumber){
        List<Damage> damages = damageService.getDamagesFromTable(chassisNumber);
        model.addAttribute("damages", damages);
        //Skal bruge forhåndsaftalen
        return "home/car_bought";
    }




}
