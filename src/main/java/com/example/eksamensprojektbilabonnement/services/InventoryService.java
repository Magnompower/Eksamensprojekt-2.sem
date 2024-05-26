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

    public List<Car> getAllCars() {
        return inventoryRepository.getAllCars();
    }

    public List<Car> getFilteredCars(String filterBy){
        //Returns a list of filtered cars
        if (filterBy.equals("ALL")) {
            return inventoryRepository.getAllCars();
        } else {
            return inventoryRepository.getFilteredCars(filterBy);
        }
    }

    public List<Car> setSortCriteria(String sortType, String filterBy){
        //Sets the sort criteria for the list of cars to be returned:
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


    public List<Car> checkSortAndFilterCriteria(String sortType, String filterBy) {
        //Checks whether cars needs to be sorted, filtered or both, and calls the corresponding methods:
        if (sortType == null && filterBy == null) {
            return inventoryRepository.getAllCars();
        } else if (filterBy == null) {
            filterBy = "ALL";
            return setSortCriteria(sortType, filterBy);
        } else if (sortType == null) {
            return getFilteredCars(filterBy);
        } else {
            return setSortCriteria(sortType, filterBy);
        }
    }

    public List<Car> getDamagedCars() {
        return inventoryRepository.getDamagedCars();
    }
}


