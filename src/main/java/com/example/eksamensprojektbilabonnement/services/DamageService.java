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

    public void addDamageToTable(String chassisNumber, String damageName, double damagePrice, int leaseId){
        damageRepository.addDamageToTable(chassisNumber, damageName, damagePrice, leaseId);
    }

    public List<Damage> getDamagesFromTable(String chassisNumber){
        return damageRepository.getDamagesFromTable(chassisNumber);
    }

    public List<Damage> getInvoicedDamages(String chassisNumber) {
        return damageRepository.getInvoicedDamages(chassisNumber);
    }

    public List<Damage> getNonInvoicedDamages(String chassisNumber, int leaseId) {
        return damageRepository.getNonInvoicedDamages(chassisNumber, leaseId);
    }

    public void setDamagesToInvoiced(int leaseId, String chassisNumber) {
        damageRepository.setDamagesToInvoiced(leaseId, chassisNumber);
    }
}
