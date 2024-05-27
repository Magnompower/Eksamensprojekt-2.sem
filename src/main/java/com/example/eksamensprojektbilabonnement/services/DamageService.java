package com.example.eksamensprojektbilabonnement.services;

import com.example.eksamensprojektbilabonnement.models.Damage;
import com.example.eksamensprojektbilabonnement.repositories.DamageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * The Damage service.
 */
@Service
public class DamageService {


    /**
     * The Damage repository.
     */
    @Autowired
    DamageRepository damageRepository;
    /**
     * Add damage to table.
     * @author Hasan
     *
     * @param chassisNumber the chassis number
     * @param damageName    the damage name
     * @param damagePrice   the damage price
     * @param leaseId       the lease id
     */
    public void addNonInvoicedDamage(String chassisNumber, String damageName, double damagePrice, int leaseId){
        damageRepository.addNonInvoicedDamage(chassisNumber, damageName, damagePrice, leaseId);
    }

    /**
     * Get damages from table list.
     * @author Hasan
     *
     * @param chassisNumber the chassis number
     * @return the list
     */
    public List<Damage> getDamagesFromTable(String chassisNumber){
        return damageRepository.getDamagesFromTable(chassisNumber);
    }

    /**
     * Gets invoiced damages.
     * @author Hasan
     *
     * @param chassisNumber the chassis number
     * @return the invoiced damages
     */
    public List<Damage> getInvoicedDamages(String chassisNumber) {
        return damageRepository.getInvoicedDamages(chassisNumber);
    }

    /**
     * Gets non invoiced damages.
     * @author Hasan
     *
     * @param chassisNumber the chassis number
     * @param leaseId       the lease id
     * @return the non invoiced damages
     */
    public List<Damage> getNonInvoicedDamages(String chassisNumber, int leaseId) {
        return damageRepository.getNonInvoicedDamages(chassisNumber, leaseId);
    }

    /**
     * Sets damages to invoice.
     * @author Hasan, Magne
     *
     * @param leaseId       the lease id
     * @param chassisNumber the chassis number
     */
    public void setDamagesToInvoiced(int leaseId, String chassisNumber) {
        damageRepository.setDamagesToInvoiced(leaseId, chassisNumber);
    }

    public void addDamage(String chassisNumber, String damageName, double damagePrice) {
        damageRepository.addDamage(chassisNumber, damageName, damagePrice);
    }

    public void deleteDamage(int damageId) {
        damageRepository.deleteDamage(damageId);
    }
}
