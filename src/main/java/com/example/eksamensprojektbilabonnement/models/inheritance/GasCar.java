package com.example.eksamensprojektbilabonnement.models.inheritance;

import com.example.eksamensprojektbilabonnement.utilities.FuelType;

public class GasCar extends Car{
    private double kmPerLiter;
    private FuelType fuelType;
    private double carbonEmissionPerKm;

    public GasCar(){

    }

    public double getKmPerLiter() {
        return kmPerLiter;
    }

    public void setKmPerLiter(double kmPerLiter) {
        this.kmPerLiter = kmPerLiter;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public double getCarbonEmissionPerKm() {
        return carbonEmissionPerKm;
    }

    public void setCarbonEmissionPerKm(double carbonEmissionPerKm) {
        this.carbonEmissionPerKm = carbonEmissionPerKm;
    }
}
