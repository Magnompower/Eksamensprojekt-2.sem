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
     * @author Hasan, Otto
     *
     * @param chassisNumber the chassis number
     * @param customerId    the customer id
     * @param startDate     the start date
     * @param endDate       the end date
     * @param terms         the terms
     */
    public void createLease(String chassisNumber, int customerId, LocalDate startDate, LocalDate endDate, String terms) {
        validateLeaseDates(startDate, endDate);
        leaseRepository.createLease(chassisNumber, customerId, startDate, endDate, terms);
    }

    /**
     * Validate lease dates.
     * @author Otto
     *
     * @param startDate the start date
     * @param endDate   the end date
     *
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
     * @author Otto & Hasan
     *
     * @return the leases
     *
     */
    public List<LeaseAgreement> getLeases() {
        return leaseRepository.getLeases();

    }

    /**
     * Gets active lease.
     * @author Hasan
     *
     * @param chassisNumber the chassis number
     * @return the active lease
     *
     */
    public LeaseAgreement getActiveLease(String chassisNumber) {
        return leaseRepository.getActiveLease(chassisNumber);
    }

    /**
     * Conclude lease.
     * @author Hasan
     *
     * @param leaseId the lease id
     *
     */
    public void concludeLease(int leaseId) {
        leaseRepository.concludeLease(leaseId);
    }

    /**
     * Gets lease.
     * @author Hasan
     *
     * @param leaseId the lease id
     * @return the lease
     *
     */
    public LeaseAgreement getLease(int leaseId) {
        return leaseRepository.getLease(leaseId);
    }


    /**
     * Check lease availability boolean.
     * @author Magne, Hasan
     *
     * @param carChassisNumber the car chassis number
     * @param startDateNew     the start date new
     * @param endDateNew       the end date new
     * @return the boolean
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
     * @author Otto
     *
     * @param chassisNumber the chassis number
     * @return the non concluded leases
     */
    public List<LeaseAgreement> getNonConcludedLeases(String chassisNumber) {
        return leaseRepository.getNonConcludedLeases(chassisNumber);
    }
}
