import com.example.eksamensprojektbilabonnement.services.LeaseService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LeaseServiceTest {
    @Test
    public void testExceptionThrow_invalidDates() {

        LeaseService validator = new LeaseService();
            LocalDate startDate = LocalDate.of(2023, 12, 31); // Testen burde pass, da vi har en ugyldig dato.
            LocalDate endDate = LocalDate.of(2024, 2, 1);

            assertThrows(IllegalArgumentException.class, () -> {   // () ->  er et lambda udtryk. Det gør at vi kan sikre os at metoden bliver sendt over til assertThrows metoden
                                                                    // assertThrows kører metoden og ser om den kaster en IllegalArgumentException.
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





