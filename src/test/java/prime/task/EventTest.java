package prime.task;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import prime.core.PrimeException;

public class EventTest {
    @Test
    void constructor_validRange_createsEventSuccessfully() throws Exception {
        LocalDate start = LocalDate.of(2025, 1, 1);
        LocalDate end = LocalDate.of(2025, 1, 5);

        Event event = new Event(false, "Trip", start, end);

        assertNotNull(event);
    }

    @Test
    void constructor_sameStartAndEndDate_allowed() throws Exception {
        LocalDate date = LocalDate.of(2025, 1, 1);

        Event event = new Event(false, "One-day event", date, date);

        assertNotNull(event);
    }

    @Test
    void constructor_startAfterEnd_throwsException() {
        LocalDate start = LocalDate.of(2025, 1, 10);
        LocalDate end = LocalDate.of(2025, 1, 5);

        assertThrows(IllegalArgumentException.class, () -> {
            new Event(false, "Invalid event", start, end);
        });
    }

    @Test
    void constructor_nullStart_throwsException() {
        LocalDate end = LocalDate.of(2025, 1, 5);

        assertThrows(PrimeException.class, () -> {
            new Event(false, "Null start", null, end);
        });
    }

    @Test
    void constructor_nullEnd_throwsException() {
        LocalDate start = LocalDate.of(2025, 1, 5);

        assertThrows(PrimeException.class, () -> {
            new Event(false, "Null end", start, null);
        });
    }

}
