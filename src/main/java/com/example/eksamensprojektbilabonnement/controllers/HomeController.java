package com.example.eksamensprojektbilabonnement.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index(Model model) {
//        TODO Indsæt check for employeeType
//        employeeType = determineEmployeeType(); TODO
//        switch (employeeType) {
//            case "admin" -> model.addAttribute("admin", employeeType);
//            case "damageGuy" -> model.addAttribute("damageGuy", employeetype);}
            return "home/index";
        }

    @GetMapping("/login")
    public String login() {
        return "home/login";
    }
}


