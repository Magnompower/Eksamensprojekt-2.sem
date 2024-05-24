package com.example.eksamensprojektbilabonnement.controllers;

import com.example.eksamensprojektbilabonnement.services.CarService;
import com.example.eksamensprojektbilabonnement.services.CustomerService;
import com.example.eksamensprojektbilabonnement.services.DamageService;
import com.example.eksamensprojektbilabonnement.services.LeaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
public class LeaseController {
    @Autowired
    private CarService carService;

    @Autowired
    private LeaseService leaseService;

    @Autowired
    private DamageService damageService;


    @GetMapping("/lease_overview")
    public  String leaseOverview(Model model) {
        model.addAttribute("leases", leaseService.getLeases());
        model.addAttribute("localDateTime", LocalDate.now());
        return "home/lease_overview";
    }

    @PostMapping("/createLease")
    public String createLease(Model model,
                              @RequestParam String chassisNumber,
                              @RequestParam int customerId,
                              @RequestParam LocalDate startDate,
                              @RequestParam LocalDate endDate,
                              @RequestParam String terms,
                              RedirectAttributes redirectAttributes) {

        boolean isAvailable = leaseService.checkLeaseAvailability(chassisNumber, startDate, endDate);

        if (isAvailable) {
            try {
                leaseService.createLease(chassisNumber, customerId, startDate, endDate, terms);
                return "redirect:/success.html";
            } catch (IllegalArgumentException e) {
                redirectAttributes.addFlashAttribute("error", e.getMessage());
                return "redirect:/view_car?chassisNumber=" + chassisNumber;
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("error", "Unknown Error. Try again.");
                return "redirect:/view_car?chassisNumber=" + chassisNumber;
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "Those dates are not avaliable. Please choose valid dates");
            return "redirect:/view_car?chassisNumber=" + chassisNumber;
        }
    }

    @PostMapping ("/conclude_lease")
    public String concludeLease(@RequestParam int leaseId, @RequestParam String chassisNumber){
        //Set lease to concluded:
        leaseService.concludeLease(leaseId);

        //Set the car to avaliable:
        String carTable = carService.getCarTable(chassisNumber);
        carService.updateCarState(chassisNumber, "AVAILABLE", carTable);

        //Set the damages to invoiced:
        damageService.setDamagesToInvoiced(leaseId, chassisNumber);

        //isActive should be set to false. Also think about that functionality here should be triggers instead
        return "redirect:/returned_cars";
    }


}
