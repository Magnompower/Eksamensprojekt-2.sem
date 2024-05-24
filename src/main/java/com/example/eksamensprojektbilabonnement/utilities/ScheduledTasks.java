package com.example.eksamensprojektbilabonnement.utilities;

import com.example.eksamensprojektbilabonnement.repositories.CarRepository;
import com.example.eksamensprojektbilabonnement.repositories.CustomerRepository;
import com.example.eksamensprojektbilabonnement.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Component
public class ScheduledTasks {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class); // FJERNES??

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CustomerService customerService;

    @Scheduled(fixedRate = 3600000) // Every hour
    public void updateLeaseStatusesInDatabase() {
        try {
            List<String> upcomingLeases = carRepository.findCarsWithUpcomingLeases();
            for (String chassisNumber : upcomingLeases) {
                carRepository.changeCarStateInLeasedCars(chassisNumber, "GETTING_PREPARED");
                logger.info("Updated lease status for chassis: {}", chassisNumber);
            }
        } catch (Exception e) {
            logger.error("Failed to update lease statuses: ", e);
        }
    }

    @Scheduled(fixedRate = 43200000) // Every 12 hours
    public void anonymizeCustomerAfterFiveYears() {
        try {
            List<Integer> customersForAnonymization = customerService.findCustomersForAnonymization();
            for (Integer customerId : customersForAnonymization) {
                customerService.deleteCustomer(customerId);
                logger.info("Anonymized customer: {}", customerId);
            }
        } catch (Exception e) {
            logger.error("Failed to anonymize customer data", e);
        }
    }
}
