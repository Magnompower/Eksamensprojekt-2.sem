package com.example.eksamensprojektbilabonnement.utilities;
import com.example.eksamensprojektbilabonnement.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.annotation.Scheduled;

@Component
public class ScheduledTasks {

    @Autowired
    CarRepository carRepository; // SERVICE?

    @Scheduled(fixedRate = 36000000) // Runs every hour(36000000 seconds).
    public void updateDatabaseIfNeeded(){
//      TODO  carsAreBeingRented = checkForStartDatesInLeases();
//        if (carsAreBeingRented){
//            carRepository.updateCarStateIfNeeded(chassis_number);
//            Other ifs?

    }
}
