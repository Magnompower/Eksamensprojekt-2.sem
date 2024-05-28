package com.example.eksamensprojektbilabonnement.utilities;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class EmployeePageMapping {
    private Map<EmployeeType, String> employeePages; //hvorfor map heroppe og hashmap længere nede?

    public EmployeePageMapping() {
        employeePages = new HashMap<>();
        // Initialize with default pages for each employeeType
        employeePages.put(EmployeeType.ADMIN, "redirect:/admin_inventory");
        employeePages.put(EmployeeType.BUSINESS_DEVELOPER, "redirect:/rented_cars");
        employeePages.put(EmployeeType.DAMAGE_MANAGEMENT, "redirect:/damage_management_inventory");
        employeePages.put(EmployeeType.LEASE_REGISTRATION, "redirect:/lease_registration_inventory");
    }
    public String getPage(EmployeeType type) {
        return employeePages.get(type);
    }

    public void addEmployeePage(EmployeeType type, String page) {
        //Method is not being used yet, but exists if there is any need to dynamically add new users
        employeePages.put(type, page);
    }
}

