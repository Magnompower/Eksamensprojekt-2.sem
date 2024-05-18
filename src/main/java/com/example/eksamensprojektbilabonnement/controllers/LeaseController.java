package com.example.eksamensprojektbilabonnement.controllers;

import com.example.eksamensprojektbilabonnement.services.CarService;
import com.example.eksamensprojektbilabonnement.services.CustomerService;
import com.example.eksamensprojektbilabonnement.services.LeaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class LeaseController {
    @Autowired
    private CarService carService;
    @Autowired
    private LeaseService leaseService;


    //todo create controller that sends  a car and customer over so that a lease can be created
    @GetMapping("/lease_overview")
    public  String leaseOverview(Model model) {
        model.addAttribute("leases", leaseService.getLeases());
        model.addAttribute("localDateTime", LocalDate.now());
        return "home/lease_overview";
    }
    @PostMapping ("/createLease")
    public String createLease(Model model, @RequestParam String carChassisNumber, @RequestParam int customerId,
                              @RequestParam LocalDate startDate, @RequestParam LocalDate endDate,
                              @RequestParam String terms)  {
    leaseService.createLease(carChassisNumber, customerId, startDate, endDate, terms);

    model.addAttribute("leases", leaseService.getLeases());
    carService.updateCarState(carChassisNumber,"RENTED", carService.getCarTable(carChassisNumber)); //Sætter bilen som rented. Skal nok opdateres senere

        return "redirect:/success.html";
    }

}
