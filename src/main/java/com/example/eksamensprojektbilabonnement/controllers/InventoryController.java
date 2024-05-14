package com.example.eksamensprojektbilabonnement.controllers;

import com.example.eksamensprojektbilabonnement.models.LeaseAgreement;
import com.example.eksamensprojektbilabonnement.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.eksamensprojektbilabonnement.models.inheritance.Car;
import com.example.eksamensprojektbilabonnement.services.InventoryService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.rmi.dgc.Lease;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private CustomerService customerService;

    @GetMapping("/inventory")
    public String showInventory(Model model) {
        List<Car> cars = inventoryService.getAllCars(); // Assuming you have a method to retrieve all gas cars
        model.addAttribute("cars", cars);
        return "home/inventory"; // The name of your HTML template
    }
    @GetMapping ("/view_car")
    public String view_car(@RequestParam String carChassisNumber, Model model) {
        Car car = inventoryService.getCarByChassisNumber(carChassisNumber);
        model.addAttribute("Car", car);
        model.addAttribute("Customers", customerService.getAllCustomers());
        return "home/view_car";
    }

    @PostMapping("/update_car")
    public String updateCar(@RequestParam String chassisNumber, @RequestParam String CarState) {
        inventoryService.updateCarState(chassisNumber, CarState);
        return "redirect:/inventory";
    }

    @GetMapping ("/sort_and_filter_cars")
    public String SortCars(@RequestParam String sortType, @RequestParam String filterBy, RedirectAttributes redirectAttributes){
      List<Car> sortedCars = inventoryService.getSortedCars(sortType, filterBy);
      redirectAttributes.addFlashAttribute("sortedCars", sortedCars);
      return "redirect:/show_inventory_sorted";
    }

    @GetMapping("/show_inventory_sorted")
    public String showInventorySorted(@ModelAttribute("sortedCars") List<Car> sortedCars, Model model) {
        model.addAttribute("cars", sortedCars);
        return "home/inventory";

    }

    @GetMapping("/leasedCars")
    public String getLeasedCars(Model model) {
        List<Car> leasedCars = inventoryService.getLeasedCars();
        double totalPrice = leasedCars.stream().mapToDouble(Car::getPrice).sum();
        model.addAttribute("leasedCars", leasedCars);
        model.addAttribute("totalPrice", totalPrice);
        return "home/leasedCars";
    }
}
