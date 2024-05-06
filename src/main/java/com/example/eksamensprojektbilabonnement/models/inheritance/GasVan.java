package com.example.eksamensprojektbilabonnement.models.inheritance;

public class GasVan extends GasCar{
    private double floorLength;
    private double maxHeightMm;
    private double maxWidthMm;

    public GasVan(){

    }

    public double getFloorLength() {
        return floorLength;
    }

    public void setFloorLength(double floorLength) {
        this.floorLength = floorLength;
    }

    public double getMaxHeightMm() {
        return maxHeightMm;
    }

    public void setMaxHeightMm(double maxHeightMm) {
        this.maxHeightMm = maxHeightMm;
    }

    public double getMaxWidthMm() {
        return maxWidthMm;
    }

    public void setMaxWidthMm(double maxWidthMm) {
        this.maxWidthMm = maxWidthMm;
    }
}
