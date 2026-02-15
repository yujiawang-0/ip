package prime.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import prime.core.PrimeException;

/**
 * Represents a ToDo with a specific due date
 *
 * A {@code Deadline} is a type of {@link ToDo} that must be completed
 * by a given date.
 */
public class Deadline extends ToDo {
    /** Type identifier used when saving this task to storage and for Ui. */
    private final static String TYPE = "D";

    /** The due date of the deadline. */
    private LocalDate due;

    /**
     * Creates a deadline task with a due date.
     *
     * @param isDone Whether the task is marked as completed
     * @param Task   The task description
     * @param due    The due date of the task
     */
    public Deadline(boolean isDone, String task, LocalDate due) {
        super(isDone, task);
        setDue(due);
    }

    public LocalDate getDue() {
        return this.due;
    }

    public void setDue(LocalDate dueDate) {
        this.due = dueDate;
    }

    /**
     * Returns the due date formatted for display to the user.
     *
     * @return The formatted due date string
     */
    private String getDueString() {
        return this.getDue().format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Returns a user-friendly string representation of this deadline.
     *
     * @return A formatted string showing the task status, description, and due date
     */
    @Override
    public String printTask() {
        String symbol = this.isDone() ? "X" : " ";
        return "[" + TYPE + "]" + "[" + symbol + "] " + getTask() + " (By: " + getDueString() + ")";

    }

    /**
     * Converts this deadline into a string format suitable for file storage.
     *
     * @return A formatted string representing this deadline for saving to file
     */
    @Override
    public String toFileString() {
        String symbol = this.isDone() ? "1" : "0";
        return TYPE + " | " + symbol + " | " + getTask() + " | " + getDue();
    }

    /**
     * Update fields for deadlines
     */
    @Override
    public void updateField(String field, String newValue) throws PrimeException {
        switch (field) {
        case "desc":
            super.updateField(field, newValue);
            break;
            
        case "by":
            try {
                setDue(LocalDate.parse(newValue));
            } catch (DateTimeParseException e) {
                throw new PrimeException("!! : Invalid date format. Please use YYYY-MM-DD.");
            }
            return;

        default:
            throw new PrimeException("!! : Invalid field for Deadline type");
        }
    }
}
