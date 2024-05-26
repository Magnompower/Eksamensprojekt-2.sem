package com.example.eksamensprojektbilabonnement.models;

public class Damage {

    private String damageName;
    private double damagePrice;
    private boolean invoiced;

    public Damage() {

    }

    public boolean isInvoiced() {
        return invoiced;
    }

    public void setInvoiced(boolean invoiced) {
        this.invoiced = invoiced;
    }


    public String getDamageName() {
        return damageName;
    }

    public void setDamageName(String damageName) {
        this.damageName = damageName;
    }

    public double getDamagePrice() {
        return damagePrice;
    }

    public void setDamagePrice(double damagePrice) {
        this.damagePrice = damagePrice;
    }

}

