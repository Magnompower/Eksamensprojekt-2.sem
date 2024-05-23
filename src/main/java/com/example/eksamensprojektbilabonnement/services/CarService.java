package com.example.eksamensprojektbilabonnement.services;
import com.example.eksamensprojektbilabonnement.models.inheritance.Car;
import com.example.eksamensprojektbilabonnement.models.inheritance.GasCar;
import com.example.eksamensprojektbilabonnement.repositories.CarRepository;
import com.example.eksamensprojektbilabonnement.utilities.CarState;
import com.example.eksamensprojektbilabonnement.utilities.FuelType;
import com.example.eksamensprojektbilabonnement.utilities.GenerateRandomCar;
import com.example.eksamensprojektbilabonnement.utilities.TransmissionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    private final GenerateRandomCar generateRandomCar;


    @Autowired
    private CarRepository carRepository;

    public CarService(GenerateRandomCar generateRandomCar) {
        this.generateRandomCar = generateRandomCar;
    }

    public List<GasCar> generateRandomCars(int count) {
        return GenerateRandomCar.generateRandomCars(count);
    }

    public void createCar(String chassisNumber, String carModel, String brand, double price, double registrationFee, double kmPerLiter, double carbonEmissionPerKm, String licensePlate, CarState carState, TransmissionType transmissionType, FuelType fuelType, String image_url) {
        carRepository.createCar(chassisNumber, carModel, brand, price, registrationFee, kmPerLiter, carbonEmissionPerKm, licensePlate, carState, transmissionType, fuelType, image_url);
    }

    public Car getCarByChassisNumber(String chassisNumber) {
        return carRepository.getCarByChassisNumber(chassisNumber);
    }



    public String getCarTable(String chassisNumber) {
        return carRepository.getCarTable(chassisNumber);
    }

    public void updateCarState(String chassisNumber, String carState, String carTable) {
        carRepository.updateCarState(chassisNumber, carState, carTable.toLowerCase());
    }


    public void updateKmDriven(String chassisNumber, double kmDriven, String carTable) {
        carRepository.updateKmDriven(chassisNumber, kmDriven, carTable);
    }
}
