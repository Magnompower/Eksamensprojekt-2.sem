import com.example.eksamensprojektbilabonnement.services.LeaseService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LeaseServiceTest {
    @Test
    public void testExceptionThrow () {

        LeaseService validator = new LeaseService();
            LocalDate startDate = LocalDate.of(2023, 12, 31); // Testen burde pass, da vi har en ugyldig dato.
            LocalDate endDate = LocalDate.of(2024, 2, 1);

            assertThrows(IllegalArgumentException.class, () -> {
                validator.validateLeaseDates(startDate, endDate);
            }, "Expected to throw IllegalArgumentException when start date is before minDate");
        }

    @Test
    public void testValidateLeaseDates_validDates() {
        LeaseService validator = new LeaseService();
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2026, 12, 31);


        assertDoesNotThrow(() -> {
            validator.validateLeaseDates(startDate, endDate);
        }, "Should not throw an exception for valid dates");
    }
}





