package com.example.eksamensprojektbilabonnement.utilities;

import com.example.eksamensprojektbilabonnement.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Component
@EnableScheduling
public class ScheduledTasks {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    @Autowired
    private CarRepository carRepository;

    @Scheduled(fixedRate = 3600000) // Runs every hour
    public void updateLeaseStatusesInDatabase() {
        try {
            List<String> upcomingLeases = carRepository.findCarsWithUpcomingLeases();
            for (String chassisNumber : upcomingLeases) {
                carRepository.changeCarStateInLeasedCars(chassisNumber, "GETTING_PREPARED");
                logger.info("Updated lease status for chassis: {}", chassisNumber);
            }
        } catch (Exception e) {
            logger.error("Failed to update lease statuses", e);
        }
    }
}
