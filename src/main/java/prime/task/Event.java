package prime.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import prime.core.PrimeException;

/**
 * Represents an event task with a start date and an end date.
 *
 * An {@code Event} is a type of {@link ToDo} that occurs over a period of time
 */
public class Event extends ToDo {
    private static final String TYPE = "E";
    private LocalDate startTime;
    private LocalDate endTime;

    /**
     * Constructs an Event task.
     *
     * @param isDone    Whether the event is marked as completed.
     * @param Task      The description of the event.
     * @param startTime The start date of the event.
     * @param endTime   The start date of the event.
     */
    public Event(boolean isDone, String task, LocalDate startTime, LocalDate endTime) {
        super(isDone, task);
        setStart(startTime);
        setEnd(endTime);
    }

    public LocalDate getStart() {
        return this.startTime;
    }

    public LocalDate getEnd() {
        return this.endTime;
    }

    public void setStart(LocalDate start) {
        this.startTime = start;
    }

    public void setEnd(LocalDate end) {
        this.endTime = end;
    }

    private String getStartString() {
        return this.getStart().format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    private String getEndString() {
        return this.getEnd().format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Returns a user-friendly string representation of the event.
     *
     * @return The formatted event string for display.
     */
    @Override
    public String printTask() {
        String symbol = this.isDone() ? "X" : " ";
        return "[" + TYPE + "]" + "[" + symbol + "] " + getTask() + " (From: "
                + getStartString() + " To: " + getEndString() + ")";
    }

    /**
     * Returns the file storage format of the event.
     *
     * @return A string representation suitable for saving to file.
     */
    @Override
    public String toFileString() {
        String symbol = this.isDone() ? "1" : "0";
        return TYPE + " | " + symbol + " | " + getTask() + " | "
                + getStart() + " | " + getEnd();
    }

    /**
     * Updates fields for Events
     */
    @Override
    public void updateField(String field, String newValue) throws PrimeException {
        switch (field) {
        case "desc":
            super.updateField(field, newValue);
            break;

        case "from":
            try {
                setStart(LocalDate.parse(newValue));
            } catch (DateTimeParseException e) {
                throw new PrimeException("!! : Invalid date format. Please use YYYY-MM-DD.");
            }
            return;

        case "to":
            try {
                setEnd(LocalDate.parse(newValue));
            } catch (DateTimeParseException e) {
                throw new PrimeException("!! : Invalid date format. Please use YYYY-MM-DD.");
            }
            return;

        default:
            throw new PrimeException("!! : Invalid field for Event type");
        }
    }
}
