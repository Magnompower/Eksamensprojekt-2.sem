package com.example.eksamensprojektbilabonnement.models;

public class ConditionReport {

    private int leaseId;
    private String chassisNumber;
    private Double totalExtraCost;
    private Double kmBeforeLease;
    private Double kmAfterLease;


    public ConditionReport() {
    }

    public int getLeaseId() {
        return leaseId;
    }

    public void setLeaseId(int customerId) {
        this.leaseId = customerId;
    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    public Double getTotalExtraCost() {
        return totalExtraCost;
    }

    public void setTotalExtraCost(Double totalExtraCost) {
        this.totalExtraCost = totalExtraCost;
    }

    public Double getKmBeforeLease() {
        return kmBeforeLease;
    }

    public void setKmBeforeLease(Double kmBeforeLease) {
        this.kmBeforeLease = kmBeforeLease;
    }

    public Double getKmAfterLease() {
        return kmAfterLease;
    }

    public void setKmAfterLease(Double kmAfterLease) {
        this.kmAfterLease = kmAfterLease;
    }


}
