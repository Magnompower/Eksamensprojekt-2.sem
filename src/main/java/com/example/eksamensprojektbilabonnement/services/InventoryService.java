package com.example.eksamensprojektbilabonnement.services;
import com.example.eksamensprojektbilabonnement.models.inheritance.Car;
import com.example.eksamensprojektbilabonnement.repositories.InventoryRepository;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private InventoryRepository inventoryRepository;

    public Car getCarById(int id){
        return inventoryRepository.getCarById(id);
    }
}
