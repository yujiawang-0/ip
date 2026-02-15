package prime.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import prime.core.PrimeException;

public class DeadlineTest {
    // CONSTRUCTOR tests
    @Test
    void constructor_validDeadline_createsSuccessfully() {
        LocalDate due = LocalDate.of(2026, 2, 28);
        Deadline deadline = new Deadline(false, "Submit report", due);
        assertEquals(due, deadline.getDue());
    }

    @Test
    void constructor_nullDue_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Deadline(false, "Task", null);
        });
    }


    // Update field test
    @Test
    void updateField_validDate_updatesSuccessfully() throws PrimeException {
        Deadline deadline = new Deadline(false, "Submit report",
                LocalDate.of(2026, 2, 28));
        deadline.updateField("by", "2026-03-01");
        assertEquals(LocalDate.of(2026, 3, 1), deadline.getDue());
    }

    @Test
    void updateField_invalidCalendarDate_throwsPrimeException() {
        Deadline deadline = new Deadline(false, "Submit report",
                LocalDate.of(2026, 2, 28));
        assertThrows(PrimeException.class, () -> {
            deadline.updateField("by", "2026-02-30");
        });
    }

    @Test
    void updateField_invalidFormat_throwsPrimeException() {
        Deadline deadline = new Deadline(false, "Submit report",
                LocalDate.of(2026, 2, 28));
        assertThrows(PrimeException.class, () -> {
            deadline.updateField("by", "not-a-date");
        });
    }

    @Test
    void updateField_invalidField_throwsPrimeException() {
        Deadline deadline = new Deadline(false, "Submit report",
                LocalDate.of(2026, 2, 28));
        assertThrows(PrimeException.class, () -> {
            deadline.updateField("random", "2026-03-01");
        });
    }

    // Printing tests
    @Test
    void printTask_formatsCorrectly() {
        Deadline deadline = new Deadline(false, "Submit report",
                LocalDate.of(2026, 2, 28));
        String output = deadline.printTask();
        assertTrue(output.contains("[D]"));
        assertTrue(output.contains("Submit report"));
        assertTrue(output.contains("Feb 28 2026"));
    }

    @Test
    void toFileString_formatsCorrectly() {
        Deadline deadline = new Deadline(true, "Submit report",
                LocalDate.of(2026, 2, 28));
        String fileString = deadline.toFileString();
        assertEquals("D | 1 | Submit report | 2026-02-28", fileString);
    }

}
