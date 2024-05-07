package com.example.eksamensprojektbilabonnement.models.inheritance;

import com.example.eksamensprojektbilabonnement.utilities.CarState;
import com.example.eksamensprojektbilabonnement.utilities.TransmissionType;

public abstract class Car { //abstract fordi der ikke skal oprettes instanser af den her klasse
    private int vehicleId;
    private String chassisNumber;
    private String licensePlateNumber;
    private String brand;
    private String model;
    private TransmissionType transmissionType;
    private double registrationFee;
    private CarState carState;
    private double price;
    private String image_url;



    public Car(){

    }
    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId;
    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public TransmissionType getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(TransmissionType transmissionType) {
        this.transmissionType = transmissionType;
    }

    public double getRegistrationFee() {
        return registrationFee;
    }

    public void setRegistrationFee(double registrationFee) {
        this.registrationFee = registrationFee;
    }

    public CarState getCarState() {
        return carState;
    }

    public void setCarState(CarState carState) {
        this.carState = carState;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
