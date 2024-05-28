package com.example.eksamensprojektbilabonnement.services;

import com.example.eksamensprojektbilabonnement.models.LeaseAgreement;
import com.example.eksamensprojektbilabonnement.repositories.LeaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


/**
 * The Lease service.
 */
@Service
public class LeaseService {

    /**
     * The Lease repository.
     */
    @Autowired
    LeaseRepository leaseRepository;

    /**
     * Create lease.
     *
     * @param chassisNumber the chassis number
     * @param customerId    the customer id
     * @param startDate     the start date
     * @param endDate       the end date
     * @author Hasan, Otto
     */
    public void createLease(String chassisNumber, int customerId, LocalDate startDate, LocalDate endDate) {
        validateLeaseDates(startDate, endDate);
        leaseRepository.createLease(chassisNumber, customerId, startDate, endDate);
    }

    /**
     * Validate lease dates.
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @author Otto
     */
    public void validateLeaseDates(LocalDate startDate, LocalDate endDate) {
        LocalDate minDate = LocalDate.of(2024, 1, 1);
        LocalDate maxDate = LocalDate.of(2026, 12, 31);
        // her sikre vi at de datoer kontrakterne har er gyldige.
        if (startDate.isBefore(minDate) || startDate.isAfter(maxDate) ||
                endDate.isBefore(minDate) || endDate.isAfter(maxDate) ||
                endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("Dates must be between 2024, and 2026, and end date must be after start date.");
        }
    }

    /**
     * Gets leases.
     *
     * @return the leases
     * @author Otto & Hasan
     */
    public List<LeaseAgreement> getLeases() {
        return leaseRepository.getLeases();

    }

    /**
     * Gets active lease.
     *
     * @param chassisNumber the chassis number
     * @return the active lease
     * @author Hasan
     */
    public LeaseAgreement getActiveLease(String chassisNumber) {
        return leaseRepository.getActiveLease(chassisNumber);
    }

    /**
     * Conclude lease.
     *
     * @param leaseId the lease id
     * @author Hasan
     */
    public void concludeLease(int leaseId) {
        leaseRepository.concludeLease(leaseId);
    }

    /**
     * Gets lease.
     *
     * @param leaseId the lease id
     * @return the lease
     * @author Hasan
     */
    public LeaseAgreement getLease(int leaseId) {
        return leaseRepository.getLease(leaseId);
    }


    /**
     * Check lease availability boolean.
     *
     * @param carChassisNumber the car chassis number
     * @param startDateNew     the start date new
     * @param endDateNew       the end date new
     * @return the boolean
     * @author Magne, Hasan
     */
    public boolean checkLeaseAvailability(String carChassisNumber, LocalDate startDateNew, LocalDate endDateNew) {
        // Check if the starting date is later than the end date
        if (startDateNew.isAfter(endDateNew)) {
            return false;
        }

        List<LeaseAgreement> nonConcludedLeases = getNonConcludedLeases(carChassisNumber);

        for (LeaseAgreement lease : nonConcludedLeases) {
            if (isOverlapping(lease.getStartDate(), lease.getEndDate(), startDateNew, endDateNew)) {
                return false;
            }
        }
        return true;
    }

    private boolean isOverlapping(LocalDate existingStart, LocalDate existingEnd, LocalDate newStart, LocalDate newEnd) {
        return !(newEnd.isBefore(existingStart) || newStart.isAfter(existingEnd));
    }


    /**
     * Gets non concluded leases.
     *
     * @param chassisNumber the chassis number
     * @return the non concluded leases
     * @author Otto
     */
    public List<LeaseAgreement> getNonConcludedLeases(String chassisNumber) {
        return leaseRepository.getNonConcludedLeases(chassisNumber);
    }

    public void setLeaseInactive(int leaseId) {
        leaseRepository.setLeaseInactive(leaseId);
    }
}
