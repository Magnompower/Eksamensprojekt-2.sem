package com.example.eksamensprojektbilabonnement.services;

import com.example.eksamensprojektbilabonnement.repositories.LeaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class LeaseService {
    @Autowired
    LeaseRepository leaseRepository;

    public void createLease(String carChassisNumber, int customerId, LocalDate startDate, LocalDate endDate, String terms) {
        leaseRepository.createLease(carChassisNumber, customerId, startDate, endDate, terms);
    }
}
