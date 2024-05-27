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


/**
 * The Car service.
 */
@Service
public class CarService {
    private final GenerateRandomCar generateRandomCar;

    @Autowired
    private CarRepository carRepository;

    /**
     * Instantiates a new Car service.
     * @author Otto
     *
     * @param generateRandomCar the generate random car
     */
    public CarService(GenerateRandomCar generateRandomCar) {
        this.generateRandomCar = generateRandomCar;
    }

    /**
     * Generate random cars list.
     * @author Otto
     *
     * @param count the count
     * @return the list
     */
    public List<GasCar> generateRandomCars(int count) {
        return GenerateRandomCar.generateRandomCars(count);
    }

    /**
     * Create car.
     * @author Hasan, Otto
     *
     * @param chassisNumber       the chassis number
     * @param carModel            the car model
     * @param brand               the brand
     * @param price               the price
     * @param registrationFee     the registration fee
     * @param kmPerLiter          the km per liter
     * @param carbonEmissionPerKm the carbon emission per km
     * @param licensePlate        the license plate
     * @param carState            the car state
     * @param transmissionType    the transmission type
     * @param fuelType            the fuel type
     * @param image_url           the image url
     */
    public void createCar(String chassisNumber, String carModel, String brand, double price, double registrationFee, double kmPerLiter, double carbonEmissionPerKm, String licensePlate, CarState carState, TransmissionType transmissionType, FuelType fuelType, String image_url) {
        carRepository.createCar(chassisNumber, carModel, brand, price, registrationFee, kmPerLiter, carbonEmissionPerKm, licensePlate, carState, transmissionType, fuelType, image_url);
    }

    /**
     * Gets car by chassis number.
     * @author Hasan, Otto
     *
     * @param chassisNumber the chassis number
     * @return the car by chassis number
     */
    public Car getCarByChassisNumber(String chassisNumber) {
        return carRepository.getCarByChassisNumber(chassisNumber);
    }

    /**
     * Gets car table.
     * @author Hasan, Anders
     *
     * @param chassisNumber the chassis number
     * @return the car table
     */
    public String getCarTable(String chassisNumber) {
        return carRepository.getCarTable(chassisNumber);
    }

    /**
     * Update car state.
     * @author Magne
     *
     * @param chassisNumber the chassis number
     * @param carState      the car state
     * @param carTable      the car table
     */
    public void updateCarState(String chassisNumber, String carState, String carTable) {
        carRepository.updateCarState(chassisNumber, carState, carTable.toLowerCase());
    }

    /**
     * Update km driven.
     * @author Magne, Hasan
     *
     * @param chassisNumber the chassis number
     * @param kmDriven      the km driven
     * @param carTable      the car table
     */
    public void updateKmDriven(String chassisNumber, double kmDriven, String carTable) {
        carRepository.updateKmDriven(chassisNumber, kmDriven, carTable);
    }

    /**
     * Gets car type by chassis number.
     * @author Anders
     *
     * @param chassisNumber the chassis number
     * @return the car type by chassis number
     */
    public String getCarTypeByChassisNumber(String chassisNumber) {
        String carTable = carRepository.getCarTable(chassisNumber);
        return switch (carTable) {
            case "GAS_CARS", "GAS_VANS" -> "Gas";
            case "ELECTRIC_CARS", "ELECTRIC_VANS" -> "Electric";
            default -> "Unknown";
        };
    }
    public List<String> findCarsWithUpcomingLeases() {
       return carRepository.findCarsWithUpcomingLeases();
    }

    public void changeCarStateInLeasedCars(String chassisNumber, String gettingPrepared) {
        carRepository.changeCarStateInLeasedCars(chassisNumber,gettingPrepared);
    }
}
