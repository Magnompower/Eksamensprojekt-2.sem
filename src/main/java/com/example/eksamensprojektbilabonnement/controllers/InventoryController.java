package com.example.eksamensprojektbilabonnement.controllers;


import com.example.eksamensprojektbilabonnement.services.CarService;
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


/**
 * The Inventory controller.
 */
@Controller
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private CarService carService;
    /**
     * Show inventory string.
     * @author Otto, Hasan, Magne, Anders
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/admin_inventory")
    public String showAdminInventory(Model model) {
        // TODO Flyt logik til service
        List<Car> cars = inventoryService.getAllCars();
        for (Car car : cars){
            car.setCarType(carService.getCarTypeByChassisNumber(car.getChassisNumber()));
        }
        model.addAttribute("cars", cars);
        return "home/admin/admin_inventory";
    }

    @GetMapping("/lease_registration_inventory")
    public String showLeaseRegistrationInventory(Model model) {
    // TODO Flyt logik til service
        List<Car> cars = inventoryService.getAllCars();
        for (Car car : cars){
            car.setCarType(carService.getCarTypeByChassisNumber(car.getChassisNumber()));
        }
        model.addAttribute("cars", cars);
        return "home/lease_registration/lease_registration_inventory";
    }

    @GetMapping("/damage_management_inventory")
    public String showDamageManagementInventory(Model model){
        List<Car> damagedCars = inventoryService.getDamagedCars();
        model.addAttribute("damagedCars", damagedCars);
        return "home/damage_management/damage_management_inventory";
    }


    /**
     * Sort cars string.
     * @author Hasan
     *
     * @param sortType           the sort type
     * @param filterBy           the filter by
     * @param redirectAttributes the redirect attributes
     * @return the string
     */
    @GetMapping("/sort_and_filter_cars")
    public String sortCars(@RequestParam(required = false) String sortType, @RequestParam(required = false) String filterBy, RedirectAttributes redirectAttributes) {
        //Returns a list of sorted and/or filtered cars, based on provided strings:
        List<Car> cars = inventoryService.checkSortAndFilterCriteria(sortType, filterBy);
        //Adds the sorted and/or filtered cars to a flashAttribute, and redirects:
        redirectAttributes.addFlashAttribute("cars", cars);
        return "redirect:/show_inventory_sorted";
    }

    /**
     * Show inventory sorted string.
     * @author Hasan
     *
     * @param cars  the cars
     * @param model the model
     * @return the string
     */
    @GetMapping("/show_inventory_sorted")
    public String showInventorySorted(@ModelAttribute("cars") List<Car> cars, Model model) {
        model.addAttribute("cars", cars);
        return "home/lease_registration/lease_registration_inventory";
    }

    /**
     * Show rented cars string.
     * @author Magne, Hasan, Anders
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/rented_cars")
    public String showRentedCars(Model model) {
        List<Car> rentedCars = inventoryService.getFilteredCars("RENTED");
        //Calculated the total price of all rented cars:
        double totalPrice = rentedCars.stream().mapToDouble(Car::getPrice).sum();
        model.addAttribute("rentedCars", rentedCars);
        model.addAttribute("totalPrice", totalPrice);
        return "home/business_developer/rented_cars";
    }


    /**
     * Show returned cars string.
     * @author: Hasan
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/returned_cars")
    public String showReturnedCars(Model model) {
        List<Car> returnedCars = inventoryService.getFilteredCars("RETURNED");
        model.addAttribute("returnedCars", returnedCars);
        return "home/damage_management/returned_cars";
    }
}
