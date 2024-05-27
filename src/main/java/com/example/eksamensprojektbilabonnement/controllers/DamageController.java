package com.example.eksamensprojektbilabonnement.controllers;

import com.example.eksamensprojektbilabonnement.models.Damage;
import com.example.eksamensprojektbilabonnement.models.inheritance.Car;
import com.example.eksamensprojektbilabonnement.services.CarService;
import com.example.eksamensprojektbilabonnement.services.DamageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;


/**
 * The Damage controller.
 */


/**
 * The Damage controller.
 */
@Controller
public class DamageController {


    /**
     * The Damage service.
     */
    @Autowired
    DamageService damageService;

    /**
     * The Car service.
     */
    @Autowired
    CarService carService;

    /**
     * Add damage string.
     *
     * @param damageName         the damage name
     * @param damagePrice        the damage price
     * @param chassisNumber      the chassis number
     * @param leaseId            the lease id
     * @param redirectAttributes the redirect attributes
     * @return the string
     * @author Hasan
     */
    @PostMapping("/add_non_invoiced_damage")
    public String addNonInvoicedDamage(@RequestParam String damageName, @RequestParam double damagePrice,
                            @RequestParam String chassisNumber, @RequestParam int leaseId,
                            RedirectAttributes redirectAttributes){
        damageService.addNonInvoicedDamage(chassisNumber, damageName, damagePrice, leaseId);
        redirectAttributes.addAttribute("leaseId", leaseId);
        return "redirect:/add_damages_to_report";
    }

    /**
     * Add damage string.
     *
     * @param damageName         the damage name
     * @param damagePrice        the damage price
     * @param chassisNumber      the chassis number
     * @param redirectAttributes the redirect attributes
     * @return the string
     * @author Magne
     */
    @PostMapping("/add_damage")
    public String addDamage(@RequestParam String damageName, @RequestParam double damagePrice,
                            @RequestParam String chassisNumber,
                            RedirectAttributes redirectAttributes){
        damageService.addDamage(chassisNumber, damageName, damagePrice);
        redirectAttributes.addAttribute("chassisNumber", chassisNumber);
        return "redirect:/view_car_damages";
    }

    /**
     * View car damages string.
     *
     * @param chassisNumber the chassis number
     * @param model         the model
     * @return the string
     * @author Otto
     */
    @GetMapping("/view_car_damages")
    public String viewCarDamages(@RequestParam String chassisNumber, Model model) {
        Car car = carService.getCarByChassisNumber(chassisNumber);
        model.addAttribute("car", car);
        List<Damage> invoicedDamages = damageService.getInvoicedDamages(chassisNumber);
        model.addAttribute("invoicedDamages", invoicedDamages);
        return "home/damage_management/view_car_damages";
    }

    /**
     * Delete damage string.
     *
     * @param damageId           the damage id
     * @param chassisNumber      the chassis number
     * @param redirectAttributes the redirect attributes
     * @return the string
     * @author Magne
     */
    @PostMapping("delete_damage")
    public String deleteDamage(@RequestParam int damageId, @RequestParam String chassisNumber,
    RedirectAttributes redirectAttributes){
        damageService.deleteDamage(damageId);
        redirectAttributes.addAttribute("chassisNumber", chassisNumber);
        return "redirect:/view_car_damages";
    }


}
