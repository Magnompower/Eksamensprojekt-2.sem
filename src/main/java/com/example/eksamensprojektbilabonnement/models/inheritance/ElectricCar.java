package com.example.eksamensprojektbilabonnement.models.inheritance;

public class ElectricCar extends Car{
    private double kmPerCharge;

    public ElectricCar(){
    }

    public double getKmPerCharge() {
        return kmPerCharge;
    }

    public void setKmPerCharge(double kmPerCharge) {
        this.kmPerCharge = kmPerCharge;
    }
}
