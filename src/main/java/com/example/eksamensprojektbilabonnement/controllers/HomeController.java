package com.example.eksamensprojektbilabonnement.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * The Home controller.
 */
@Controller
public class HomeController {
    /**
     * Index string.
     * @author Otto, Hasan, Magne, Anders
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/")
    public String index(Model model) {
            return "home/index";
        }
}


