package prime.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a start date and an end date.
 * 
 * An {@code Event} is a type of {@link ToDo} that occurs over a period of time
 */
public class Event extends ToDo {
    private String Type = "E";
    private LocalDate startTime;
    private LocalDate endTime;

    /**
     * Constructs an Event task.
     * 
     * @param isDone    Whether the event is marked as completed.
     * @param Task  The description of the event.
     * @param startTime The start date of the event.
     * @param endTime   The start date of the event.
     */
    public Event(boolean isDone, String Task, LocalDate startTime, LocalDate endTime) {
        super(isDone, Task);
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
        String symbol = this.getDone() ? "X" : " ";
        return "[" + Type + "]" + "[" + symbol + "] " + getTask() + " (From: "
                + getStartString() + " To: " + getEndString() + ")";
    }

    /**
     * Returns the file storage format of the event.
     *
     * @return A string representation suitable for saving to file.
     */
    @Override
    public String toFileString() {
        String symbol = this.getDone() ? "1" : "0";
        return Type + " | " + symbol + " | " + getTask() + " | "
                + getStart() + " | " + getEnd();
    }
}
