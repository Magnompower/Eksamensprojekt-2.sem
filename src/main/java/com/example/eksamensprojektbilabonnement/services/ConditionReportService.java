package com.example.eksamensprojektbilabonnement.services;

import com.example.eksamensprojektbilabonnement.models.ConditionReport;
import com.example.eksamensprojektbilabonnement.repositories.ConditionReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * The Condition report service.
 */
@Service
public class ConditionReportService {


    /**
     * The Condition report repository.
     */
    @Autowired
    ConditionReportRepository conditionReportRepository;

    /**
     * Create condition report.
     * @author Hasan
     *
     * @param chassisNumber the chassis number
     * @param leaseId       the lease id
     * @param kmBeforeLease the km before lease
     */
    public void createConditionReport(String chassisNumber, int leaseId, double kmBeforeLease) {
        conditionReportRepository.createConditionReport(chassisNumber, leaseId, kmBeforeLease);
    }

    /**
     * Gets condition report.
     * @author Hasan
     *
     * @param leaseId the lease id
     * @return the condition report
     */
    public ConditionReport getConditionReport(int leaseId) {
        return conditionReportRepository.getConditionReport(leaseId);
    }

    /**
     * Sets km driven after lease.
     * @author Hasan
     *
     * @param leaseId  the lease id
     * @param kmDriven the km driven
     */
    public void setKmDrivenAfterLease(int leaseId, double kmDriven) {
        conditionReportRepository.setKmDrivenAfterLease(leaseId, kmDriven);
    }
}
