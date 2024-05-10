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

    public Car getCarById(int id){
        return carRepository.getCarById(id);
    }

    public String getAllCarsChassisNumber() {
        return carRepository.getAllCarsChassisNumber();
    }

    public List<GasCar> generateRandomCars(int count) {
        return GenerateRandomCar.generateRandomCars(count);
    }

    public void createCar(String carChassisNumber, String carModel, String brand, double price, double registrationFee, double kmPerLiter, double carbonEmissionPerKm, String licensePlate, CarState carState, TransmissionType transmissionType, FuelType fuelType, String image_url) {
        carRepository.createCar(carChassisNumber, carModel, brand, price, registrationFee, kmPerLiter, carbonEmissionPerKm, licensePlate, carState, transmissionType, fuelType, image_url);



    }
}
