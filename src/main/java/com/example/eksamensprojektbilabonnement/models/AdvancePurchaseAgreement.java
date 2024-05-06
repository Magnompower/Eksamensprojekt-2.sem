package com.example.eksamensprojektbilabonnement.models;

import com.example.eksamensprojektbilabonnement.models.inheritance.Car;

public class AdvancePurchaseAgreement {
    private Car car;
    private Customer customer;
    private boolean isConcluded;

    //skal også indeholde en matrix over afslag ift. skader

    public AdvancePurchaseAgreement(){

    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public boolean isConcluded() {
        return isConcluded;
    }

    public void setConcluded(boolean concluded) {
        isConcluded = concluded;
    }
}
