package com.example.eksamensprojektbilabonnement.services;

import com.example.eksamensprojektbilabonnement.models.inheritance.Car;
import com.example.eksamensprojektbilabonnement.models.inheritance.GasCar;
import com.example.eksamensprojektbilabonnement.repositories.InventoryRepository;
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

    public List<Car> getSortedCars(String sortType) {
            return switch (sortType) {
                case "priceAscending" -> inventoryRepository.getSortedCars("price", "ASC");
                case "priceDescending" -> inventoryRepository.getSortedCars("price", "DESC");
                case "brandAscending" -> inventoryRepository.getSortedCars("brand", "ASC");
                case "brandDescending" -> inventoryRepository.getSortedCars("brand", "DESC");
                default -> new ArrayList<>();
            };
        }
    }

