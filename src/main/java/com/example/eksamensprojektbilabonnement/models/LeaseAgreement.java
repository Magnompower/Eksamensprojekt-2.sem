package com.example.eksamensprojektbilabonnement.models;

import com.example.eksamensprojektbilabonnement.models.inheritance.Car;

import java.time.LocalDate;


public class LeaseAgreement {
    private int leaseId;
    private String carChassisNumber;
    private int customerId;
    private boolean isConcluded;
    private String terms;
    private LocalDate startDate;
    private LocalDate endDate;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCarChassisNumber() {
        return carChassisNumber;
    }

    public void setCarChassisNumber(String carChassisNumber) {
        this.carChassisNumber = carChassisNumber;
    }

    public int getLeaseId() {
        return leaseId;
    }

    public void setLeaseId(int leaseId) {
        this.leaseId = leaseId;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LeaseAgreement(){
    }





    public boolean isConcluded() {
        return isConcluded;
    }

    public void setConcluded(boolean concluded) {
        isConcluded = concluded;
    }
}
