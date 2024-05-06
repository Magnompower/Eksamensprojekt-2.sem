package com.example.eksamensprojektbilabonnement.models.inheritance;

public class ElectricVan extends ElectricCar{
    private double floorLengthinMm;
    private double maxHeightInMm;
    private double maxWidthInMm;

    public ElectricVan(){

    }

    public double getFloorLengthinMm() {
        return floorLengthinMm;
    }

    public void setFloorLengthinMm(double floorLengthinMm) {
        this.floorLengthinMm = floorLengthinMm;
    }

    public double getMaxHeightInMm() {
        return maxHeightInMm;
    }

    public void setMaxHeightInMm(double maxHeightInMm) {
        this.maxHeightInMm = maxHeightInMm;
    }

    public double getMaxWidthInMm() {
        return maxWidthInMm;
    }

    public void setMaxWidthInMm(double maxWidthInMm) {
        this.maxWidthInMm = maxWidthInMm;
    }
}
