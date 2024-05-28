package com.example.eksamensprojektbilabonnement.utilities;
import com.example.eksamensprojektbilabonnement.models.inheritance.Car;
import com.example.eksamensprojektbilabonnement.models.inheritance.GasCar;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
@Component
public class GenerateRandomCar {

       private static  final String model = " " ;
       private  static final String brand = " ";

        private static final double MIN_PRICE = 10000.0;
        private static final double MAX_PRICE = 50000.0;
        private static final double MIN_REGISTRATION_FEE = 500.0;
        private static final double MAX_REGISTRATION_FEE = 2000.0;
        private static final double MIN_KM_PER_LITER = 5.0;
        private static final double MAX_KM_PER_LITER = 15.0;
        private static final double MIN_CARBON_EMISSION = 50.0;
        private static final double MAX_CARBON_EMISSION = 150.0;

        private static final Random random = new Random();

        public static List<GasCar> generateRandomCars(int count) {
            List<GasCar> gasCars = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                GasCar car = new GasCar();
                car.setChassisNumber(generateRandomChassisNumber());
                car.setLicensePlateNumber(generateRandomLicensePlateNumber());
                car.setBrand("Toyota");
                car.setModel(selectModel());

                car.setCarState(CarState.AVAILABLE);
                car.setFuelType(getRandomFuelType());
                car.setTransmissionType(getRandomTransmissionType());
                car.setPrice(generateRandomPrice());
                car.setRegistrationFee(generateRandomRegistrationFee());
                car.setKmPerLiter(generateRandomKmPerLiter());
                car.setCarbonEmissionPerKm(generateRandomCarbonEmission());
                gasCars.add(car);
            }
            return gasCars;
        }
        private static String selectModel () {
            int num = random.nextInt(4)+1;
            switch (num) {
                case 1 -> {
                    return  "Corolla";
                }
                case 2 -> {
                    return "Prius";

                }
                case 3 -> {
                    return "Land Cruiser";

                }
                case 4 -> {
                    return "Camry";
                }
            } return "Corolla";
        }

        private static int generateModelId() {
            return  random.nextInt(100);
        }
        private  static int generateBrandId () {
            return  random.nextInt(20);
        }

        private static String generateRandomChassisNumber() {
            return "GC" + random.nextInt(10000);
        }

        private static String generateRandomLicensePlateNumber() {
            return "GS" + random.nextInt(10000);
        }


    private static double generateRandomPrice() {
        double price = MIN_PRICE + (MAX_PRICE - MIN_PRICE) * random.nextDouble();
        return Math.round(price * 100.0) / 100.0;
    }

        private static double generateRandomRegistrationFee() {
            double registrationFee = MIN_REGISTRATION_FEE + (MAX_REGISTRATION_FEE - MIN_REGISTRATION_FEE) * random.nextDouble();
            return Math.round(registrationFee * 100.0) / 100.0;
        }

        private static double generateRandomKmPerLiter() {
            double kmPerLiter = MIN_KM_PER_LITER + (MAX_KM_PER_LITER - MIN_KM_PER_LITER) * random.nextDouble();
            return Math.round(kmPerLiter * 100.0) / 100.0;
        }

        private static double generateRandomCarbonEmission() {
            double carbonEmisson = MIN_CARBON_EMISSION + (MAX_CARBON_EMISSION - MIN_CARBON_EMISSION) * random.nextDouble();
            return Math.round(carbonEmisson * 100.0) / 100.0;
        }


    private static FuelType getRandomFuelType() {
        FuelType[] values = FuelType.values();
        return values[random.nextInt(values.length)];
    }

    private static TransmissionType getRandomTransmissionType() {
        TransmissionType[] values = TransmissionType.values();
        return values[random.nextInt(values.length)];
    }

    public GenerateRandomCar() {
    }

}



