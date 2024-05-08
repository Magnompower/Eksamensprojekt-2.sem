package com.example.eksamensprojektbilabonnement.controllers;

import com.example.eksamensprojektbilabonnement.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.eksamensprojektbilabonnement.models.inheritance.Car;
import com.example.eksamensprojektbilabonnement.services.InventoryService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;

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

    @GetMapping ("/sort_cars")
    public String SortCars(@RequestParam String sortType, RedirectAttributes redirectAttributes){
      List<Car> sortedCars = inventoryService.getSortedCars(sortType);
      redirectAttributes.addFlashAttribute("sortedCars", sortedCars);
      return "redirect:/show_inventory_sorted";
    }

    @GetMapping("/show_inventory_sorted")
    public String showInventorySorted(@ModelAttribute("sortedCars") List<Car> sortedCars, Model model) {
        model.addAttribute("cars", sortedCars);
        return "home/inventory";
    }
}
