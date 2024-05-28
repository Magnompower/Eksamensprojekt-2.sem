package com.example.eksamensprojektbilabonnement.controllers;

import com.example.eksamensprojektbilabonnement.models.Employee;
import com.example.eksamensprojektbilabonnement.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ValidateController {

    /**
     * The Employee service.
     */
    @Autowired
    EmployeeService employeeService;


    /**
     * Validate string.
     * @author Hasan, Magne, Otto, Anders
     *
     * @param model    the model
     * @param email    the email
     * @param password the password
     * @return the string
     */
    @PostMapping("/validate")
    public String validate(Model model, @RequestParam String email, @RequestParam String password) {
        String loginStatus = employeeService.checkPassword(email, password);

        if (loginStatus.equals("UserApproved")) {
            Employee employee = employeeService.getEmployee(email);
            return employeeService.getPageForEmployee(employee.getEmployeeType());
        } else if (loginStatus.equals("NoUserFound")) {
            model.addAttribute("error", "This email does not exist in the database");
            return "home/index";
        } else {
            model.addAttribute("error", "Wrong password");
        }
        //det her logik skal heller ikke være her.
        return "home/index";
    }
}