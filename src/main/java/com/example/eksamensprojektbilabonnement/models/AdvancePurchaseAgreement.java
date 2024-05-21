package com.example.eksamensprojektbilabonnement.models;

import com.example.eksamensprojektbilabonnement.models.inheritance.Car;

public class AdvancePurchaseAgreement {
    private boolean isConcluded;

    public AdvancePurchaseAgreement(){

    }

    public boolean isConcluded() {
        return isConcluded;
    }

    public void setConcluded(boolean concluded) {
        isConcluded = concluded;
    }
}
