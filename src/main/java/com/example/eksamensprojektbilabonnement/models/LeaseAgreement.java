package com.example.eksamensprojektbilabonnement.models;

import com.example.eksamensprojektbilabonnement.models.inheritance.Car;

import java.time.LocalDate;

public class LeaseAgreement {
    private int leaseId;
    private Car car;
    private Customer customer;
    private boolean isConcluded;
    private String terms;
    private LocalDate startDate;
    private LocalDate endDate;


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

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public boolean isConcluded() {
        return isConcluded;
    }

    public void setConcluded(boolean concluded) {
        isConcluded = concluded;
    }
}
