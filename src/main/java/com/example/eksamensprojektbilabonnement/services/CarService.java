package com.example.eksamensprojektbilabonnement.services;
import com.example.eksamensprojektbilabonnement.models.inheritance.Car;
import com.example.eksamensprojektbilabonnement.repositories.CarRepository;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    private CarRepository carRepository;

    public Car getCarById(int id){
        return carRepository.getCarById(id);
    }

    public String getAllCarsChassisNumber() {
        return carRepository.getAllCarsChassisNumber();
    }
}
