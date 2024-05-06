package com.example.eksamensprojektbilabonnement.models.inheritance;

public class ElectricVan extends ElectricCar {
    private int floorLengthinMm;
    private int maxHeightInMm;
    private int maxWidthInMm;

    public ElectricVan() {

    }

    public int getFloorLengthinMm() {
        return floorLengthinMm;
    }

    public void setFloorLengthinMm(int floorLengthinMm) {
        this.floorLengthinMm = floorLengthinMm;
    }

    public int getMaxHeightInMm() {
        return maxHeightInMm;
    }

    public void setMaxHeightInMm(int maxHeightInMm) {
        this.maxHeightInMm = maxHeightInMm;
    }

    public int getMaxWidthInMm() {
        return maxWidthInMm;
    }

    public void setMaxWidthInMm(int maxWidthInMm) {
        this.maxWidthInMm = maxWidthInMm;
    }
}

