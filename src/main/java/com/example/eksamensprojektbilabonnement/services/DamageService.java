package com.example.eksamensprojektbilabonnement.services;

import com.example.eksamensprojektbilabonnement.models.Damage;
import com.example.eksamensprojektbilabonnement.repositories.DamageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DamageService {

    @Autowired
    DamageRepository damageRepository;

    public void addDamageToTable(String chassisNumber, String damage, double price){
        damageRepository.addDamageToTable(chassisNumber, damage, price);
    }

    public List<Damage> getDamagesFromTable(String chassisNumber){
        return damageRepository.getDamagesFromTable(chassisNumber);
    }
}
