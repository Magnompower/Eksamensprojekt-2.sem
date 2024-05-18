package com.example.eksamensprojektbilabonnement.controllers;
import com.example.eksamensprojektbilabonnement.services.CarService;
import com.example.eksamensprojektbilabonnement.utilities.CarState;
import com.example.eksamensprojektbilabonnement.utilities.FuelType;
import com.example.eksamensprojektbilabonnement.utilities.TransmissionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PurchaseController {
    @Autowired
    CarService carService;


    @GetMapping("/purchase")
    public String showRandomCars( Model model) {
        //Man kunne spare mange kodelinjre hvis cars blev instantieret i klassen i stedet. Man kunne slippe for alle
        // de requestparams der er i metoden under. Men så har den scope i hele klassen, og det er måske dumt med
        //en controller?
        model.addAttribute("cars",  carService.generateRandomCars(6));
        return "home/purchase";
    }



    @PostMapping("/purchaseSelected")
    public String purchaseSelected(@RequestParam("selectedCars") List<String> selectedCars,
                                   @RequestParam("carChassisNumber") List<String> carChassisNumbers,
                                   @RequestParam("carModel") List<String> carModels,
                                   @RequestParam("brand") List<String> brands,
                                   @RequestParam("price") List<Double> prices,
                                   @RequestParam("registrationFee") List<Double> registrationFees,
                                   @RequestParam("kmPerLiter") List<Double> kmPerLiters,
                                   @RequestParam("carbonEmissionPerKm") List<Double> carbonEmissionPerKms,
                                   @RequestParam("licensePlate") List<String> licensePlates,
                                   @RequestParam("carState") List<CarState> carStates,
                                   @RequestParam("transmissionType") List<TransmissionType> transmissionTypes,
                                   @RequestParam("fuelType") List<FuelType> fuelTypes) {

        if(selectedCars == null || selectedCars.isEmpty()) {  // sikre at der er valgt biler, og ellers redirecter vi tilbage
            return "redirect:/purchase";
        }
        for (String chassisNumber : selectedCars) {
                int index = carChassisNumbers.indexOf(chassisNumber);
                // Den bruger stelnumrene i selectedCars til at finde deres indeks i carChassisNumbers, som
                // indeholder alle stelnumrene i viewet. Så opretter den biler kun for dem der er selected.
                String carModel = carModels.get(index);
                String brand = brands.get(index);
                double price = prices.get(index);
                double registrationFee = registrationFees.get(index);
                double kmPerLiter = kmPerLiters.get(index);
                double carbonEmissionPerKm = carbonEmissionPerKms.get(index);
                String licensePlate = licensePlates.get(index);
                String imageUrl = null;
                CarState carState = carStates.get(index);
                TransmissionType transmissionType = transmissionTypes.get(index);
                FuelType fuelType = fuelTypes.get(index);

                carService.createCar(chassisNumber, carModel, brand, price, registrationFee, kmPerLiter,
                        carbonEmissionPerKm, licensePlate, carState, transmissionType,
                        fuelType, imageUrl);
            }
            return "redirect:/inventory";
        }
        // Her sikre vi at brugeren ikke møder et white label error page, hvis de ikke har valgt nogle biler.
        // Vi redirecter dem tilbage til purchase siden, hvor de kan vælge biler.
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public String handleMissingParams(MissingServletRequestParameterException ex) {
        String name = ex.getParameterName();
        System.out.println(name + " parameter is missing"); // dette er mest for udvikleren, så man kan se hvad der er gået galt
        return "redirect:/purchase";
    }


}
