package com.example.eksamensprojektbilabonnement.services;

import com.example.eksamensprojektbilabonnement.models.ConditionReport;
import com.example.eksamensprojektbilabonnement.repositories.ConditionReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConditionReportService {

    @Autowired
    ConditionReportRepository conditionReportRepository;

    public void createConditionReport(String chassisNumber, int leaseId, double kmBeforeLease) {
        conditionReportRepository.createConditionReport(chassisNumber, leaseId, kmBeforeLease);
    }

    public ConditionReport getConditionReport(int leaseId) {
        return conditionReportRepository.getConditionReport(leaseId);
    }

    public void setKmDrivenAfterLease(int leaseId, double kmDriven) {
        conditionReportRepository.setKmDrivenAfterLease(leaseId, kmDriven);
    }
}
