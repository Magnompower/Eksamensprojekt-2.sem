package com.example.eksamensprojektbilabonnement.services;

import com.example.eksamensprojektbilabonnement.models.LeaseAgreement;
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

    public List<Car> getFilteredCars(String filterBy){
        if (filterBy.equals("ALL")) {
            return inventoryRepository.getAllCars();
        } else {
            return inventoryRepository.getFilteredCars(filterBy);
        }
    }

    public List<Car> setSortCriteria(String sortType, String filterBy){
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
        return getSortedAndFilteredCars(sortByColumn, sortDirection, filterBy);
    }
    public List<Car> getSortedAndFilteredCars(String sortByColumn, String sortDirection, String filterBy) {
        if (filterBy.equals("ALL")) {
            return inventoryRepository.getSortedCars(sortByColumn, sortDirection);
        } else {
            return inventoryRepository.getSortedAndFilteredCars(filterBy, sortByColumn, sortDirection);
        }
    }

    public String getCarTable(String chassisNumber) {
        return inventoryRepository.getCarTable(chassisNumber);
    }


    public void updateCarState(String chassisNumber, String carState, String carTable) {
        inventoryRepository.updateCarState(chassisNumber, carState, carTable);
    }

}


