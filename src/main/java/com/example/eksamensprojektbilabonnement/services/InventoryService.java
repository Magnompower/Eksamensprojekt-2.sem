package com.example.eksamensprojektbilabonnement.services;

import com.example.eksamensprojektbilabonnement.models.inheritance.Car;
import com.example.eksamensprojektbilabonnement.models.inheritance.GasCar;
import com.example.eksamensprojektbilabonnement.repositories.InventoryRepository;
import com.example.eksamensprojektbilabonnement.utilities.CarState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryService {

    private InventoryRepository inventoryRepository;

    @Autowired
    public void setInventoryRepository(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public Car getCarByLicensePlateNumber(String licensePlateNumber) {
        return inventoryRepository.getCarByLicensePlateNumber(licensePlateNumber);
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
    public void updateCarState(String chassisNumber, String CarState) {
     //   inventoryRepository.getCarByChassisNumber(chassisNumber);
        inventoryRepository.updateCarState(chassisNumber, CarState);
    }


    public List<Car> getSortedCars(String sortType, String filterBy) {
        String sortByColumn = null;
        String sortDirection = null;

        switch (sortType) {
            case "priceAscending":
                sortByColumn = "price";
                sortDirection = "ASC";
                break;
            case "priceDescending":
                sortByColumn = "price";
                sortDirection = "DESC";
                break;
            case "brandAscending":
                sortByColumn = "brand";
                sortDirection = "ASC";
                break;
            case "brandDescending":
                sortByColumn = "brand";
                sortDirection = "DESC";
                break;
        }

        if (filterBy.equals("ALL")) {
            return inventoryRepository.getSortedCars(sortByColumn, sortDirection);
        } else {
            return inventoryRepository.getSortedAndFilteredCars(filterBy, sortByColumn, sortDirection);
        }
    }
}


