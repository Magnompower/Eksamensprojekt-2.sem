import com.example.eksamensprojektbilabonnement.models.inheritance.GasCar;
import com.example.eksamensprojektbilabonnement.utilities.GenerateRandomCar;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class GenerateRandomCarTest {
    @Test
    public void testGenerateRandomCars() {  // hvis biler bliver oprette rigtigt burde denne test være godkendt.
        GenerateRandomCar generateRandomCar = new GenerateRandomCar();
        List<GasCar> gasCars = generateRandomCar.generateRandomCars(10);
        assertEquals(10, gasCars.size());
    }

    @Test
    public void testLicensePlateLength () {
        GenerateRandomCar generateRandomCar = new GenerateRandomCar();
        List<GasCar> gasCars = generateRandomCar.generateRandomCars(10);
        for (GasCar gasCar : gasCars) {
            assertNotEquals(8, gasCar.getLicensePlateNumber().length());
        }
    }
}