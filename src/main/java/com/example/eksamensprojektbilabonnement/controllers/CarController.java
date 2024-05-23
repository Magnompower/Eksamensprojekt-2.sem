package com.example.eksamensprojektbilabonnement.controllers;

import com.example.eksamensprojektbilabonnement.models.ConditionReport;
import com.example.eksamensprojektbilabonnement.models.Damage;
import com.example.eksamensprojektbilabonnement.models.LeaseAgreement;
import com.example.eksamensprojektbilabonnement.models.inheritance.Car;
import com.example.eksamensprojektbilabonnement.services.*;
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
    private ConditionReportService conditionReportService;

    @Autowired
    private LeaseService leaseService;


    @GetMapping("/view_car")
    public String view_car(@RequestParam String chassisNumber, Model model) {
        Car car = carService.getCarByChassisNumber(chassisNumber);
        model.addAttribute("Car", car);
        model.addAttribute("Customers", customerService.getAllCustomers());
        model.addAttribute("leases", leaseService.getNonConcludedLeases(chassisNumber));
        return "home/view_car";
    }

    @PostMapping("/update_car_state")
    public String updateCar(@RequestParam String chassisNumber, @RequestParam String carState) {
        String carTable = carService.getCarTable(chassisNumber);
        carService.updateCarState(chassisNumber, carState, carTable);
        return "redirect:/inventory";
    }

    @PostMapping("/update_km_driven")
    public String updateKmDriven(@RequestParam String chassisNumber, @RequestParam double kmDriven, @RequestParam int leaseId,
                                 RedirectAttributes redirectAttributes) {
        String carTable = carService.getCarTable(chassisNumber);
        carService.updateKmDriven(chassisNumber, kmDriven, carTable);

        //Add the km driven to the condtion report:
        conditionReportService.setKmDrivenAfterLease(leaseId, kmDriven);

        redirectAttributes.addAttribute("chassisNumber", chassisNumber);
        redirectAttributes.addAttribute("leaseId", leaseId);
        return "redirect:/add_damages_to_report";
    }

    @GetMapping("/carDetails")
    public String getCarDetails(Model model, @RequestParam("chassisNumber") String chassisNumber) {
        String carType = carService.getCarTypeByChassisNumber(chassisNumber);
        Car car = carService.getCarByChassisNumber(chassisNumber);
        model.addAttribute("carType", carType);
        model.addAttribute("car", car);

        System.out.println(carType + car);

        return "home/inventory";
    }







}
