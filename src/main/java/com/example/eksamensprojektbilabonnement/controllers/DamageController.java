package com.example.eksamensprojektbilabonnement.controllers;

import com.example.eksamensprojektbilabonnement.services.DamageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


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
     * Add damage string.
     * @author Hasan
     *
     * @param damageName         the damage name
     * @param damagePrice        the damage price
     * @param chassisNumber      the chassis number
     * @param leaseId            the lease id
     * @param redirectAttributes the redirect attributes
     * @return the string
     */
    @PostMapping("/add_non_invoiced_damage")
    public String addDamage(@RequestParam String damageName, @RequestParam double damagePrice,
                            @RequestParam String chassisNumber, @RequestParam int leaseId,
                            RedirectAttributes redirectAttributes){
        damageService.addDamageToTable(chassisNumber, damageName, damagePrice, leaseId);
        redirectAttributes.addAttribute("leaseId", leaseId);
        return "redirect:/add_damages_to_report";
    }


}
