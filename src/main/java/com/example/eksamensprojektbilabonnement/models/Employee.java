package com.example.eksamensprojektbilabonnement.models;

import com.example.eksamensprojektbilabonnement.utilities.EmployeeType;

public class Employee {
    private int employeeId;
    private EmployeeType employeeType;
    private String email;
    private String password;

    public Employee(){

    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
