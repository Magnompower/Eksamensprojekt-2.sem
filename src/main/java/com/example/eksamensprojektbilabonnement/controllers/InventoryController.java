package com.example.eksamensprojektbilabonnement.controllers;

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

    @GetMapping("/inventory")
    public String showInventory(Model model) {
        List<Car> cars = inventoryService.getAllCars();
        model.addAttribute("cars", cars);
        return "home/inventory";
    }


    @GetMapping("/sort_and_filter_cars")
    public String sortCars(@RequestParam(required = false) String sortType, @RequestParam(required = false) String filterBy, RedirectAttributes redirectAttributes) {
        //Returns a list of sorted and/or filtered cars, based on provided strings:
        List<Car> cars = inventoryService.checkSortAndFilterCriteria(sortType, filterBy);
        //Adds the sorted and/or filtered cars to a flashAttribute, and redirects:
        redirectAttributes.addFlashAttribute("cars", cars);
        return "redirect:/show_inventory_sorted";
    }

    @GetMapping("/show_inventory_sorted")
    public String showInventorySorted(@ModelAttribute("cars") List<Car> cars, Model model) {
        model.addAttribute("cars", cars);
        return "home/inventory";
    }

    @GetMapping("/rented_cars")
    public String showRentedCars(Model model) {
        List<Car> rentedCars = inventoryService.getFilteredCars("RENTED");
        //Calculated the total price of all rented cars:
        double totalPrice = rentedCars.stream().mapToDouble(Car::getPrice).sum();
        model.addAttribute("rentedCars", rentedCars);
        model.addAttribute("totalPrice", totalPrice);
        return "home/rented_cars";
    }


    @GetMapping("/returned_cars")
    public String showReturnedCars(Model model) {
        List<Car> returnedCars = inventoryService.getFilteredCars("RETURNED");
        model.addAttribute("returnedCars", returnedCars);
        return "home/returned_cars";
    }
}
