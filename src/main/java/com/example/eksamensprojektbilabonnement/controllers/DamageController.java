package com.example.eksamensprojektbilabonnement.controllers;

import com.example.eksamensprojektbilabonnement.services.DamageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DamageController {

    @Autowired
    DamageService damageService;

    @PostMapping("/add_damage")
    public String addDamage(@RequestParam String damageName, @RequestParam double damagePrice,
                            @RequestParam String chassisNumber, RedirectAttributes redirectAttributes){
        damageService.addDamageToTable(chassisNumber, damageName, damagePrice);
        redirectAttributes.addAttribute("chassisNumber", chassisNumber);
        return "redirect:/car_returned";
    }


}
