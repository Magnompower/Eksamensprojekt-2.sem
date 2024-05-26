package com.example.eksamensprojektbilabonnement.utilities;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class EmployeePageMapping {
    private Map<EmployeeType, String> employeePages; //hvorfor map heroppe og hashmap længere nede?

    public EmployeePageMapping() {
        employeePages = new HashMap<>();
        // Initialize with default pages for each employeeTypr
        employeePages.put(EmployeeType.ADMIN, "redirect:/inventory");
        employeePages.put(EmployeeType.BUSINESS_DEVELOPER, "home/business_developer_dashboard");
        employeePages.put(EmployeeType.DAMAGE_MANAGEMENT, "home/damage_management_dashboard");
        employeePages.put(EmployeeType.LEASE_REGISTRATION, "home/lease_registration_dashboard");
    }

    public void addEmployeePage(EmployeeType type, String page) {
        employeePages.put(type, page);
    }

    public String getPage(EmployeeType type) {
        return employeePages.get(type);
    }
}

