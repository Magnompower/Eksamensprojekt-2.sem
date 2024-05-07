package com.example.eksamensprojektbilabonnement.services;
import com.example.eksamensprojektbilabonnement.models.inheritance.Car;
import com.example.eksamensprojektbilabonnement.models.inheritance.GasCar;
import com.example.eksamensprojektbilabonnement.repositories.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    private InventoryRepository inventoryRepository;

    @Autowired
    public void setInventoryRepository(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public Car getCarByLicensePlateNumber(String LicensePlateNumber) {
        return inventoryRepository.getCarByLicensePlateNumber(LicensePlateNumber);
    }

    public List<GasCar> getAllGasCars() {
        return inventoryRepository.getAllGasCars();
    }

    public Car getCarByChassisNumber(String carChassisNumber) {
        return inventoryRepository.getCarByChassisNumber(carChassisNumber);
    }

    public List<Car> getAllCars() {
        return inventoryRepository.getAllCars();
    }
}
