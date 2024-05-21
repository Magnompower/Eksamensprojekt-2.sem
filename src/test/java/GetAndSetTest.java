import com.example.eksamensprojektbilabonnement.models.inheritance.ElectricCar;
import com.example.eksamensprojektbilabonnement.models.inheritance.GasCar;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetAndSetTest {
    @Test
    public void testBrandSetter () {
        GasCar gasCar = new GasCar();
        gasCar.setBrand("Toyota");
        assertEquals("Toyota", gasCar.getBrand());

    }
    @Test
    public void testLicensePlateSetter () {
        ElectricCar electricCar = new ElectricCar();
        electricCar.setLicensePlateNumber("AB12345");
        assertEquals("AB12345", electricCar.getLicensePlateNumber());
    }


}
