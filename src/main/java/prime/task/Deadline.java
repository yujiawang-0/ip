package prime.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a ToDo with a specific due date
 * 
 * A {@code Deadline} is a type of {@link ToDo} that must be completed
 * by a given date.
 */
public class Deadline extends ToDo {
    /** Type identifier used when saving this task to storage and for Ui. */
    private String Type = "D";

    /** The due date of the deadline. */
    private LocalDate due;

    /**
     * Creates a deadline task with a due date.
     * 
     * @param isDone    Whether the task is marked as completed
     * @param Task  The task description
     * @param due   The due date of the task
     */
    public Deadline(boolean isDone, String Task, LocalDate due) {
        super(isDone, Task); 
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
     * @return  The formatted due date string
     */
    private String getDueString() {
        return this.getDue().format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Returns a user-friendly string representation of this deadline.
     * 
     * @return  A formatted string showing the task status, description, and due date
     */
    @Override
    public String printTask() {
        String symbol = this.getDone() ? "X" : " ";
        return "["+ Type + "]" + "[" + symbol + "] " + getTask() + " (By: " + getDueString() + ")";
        
    }

    /**
     * Converts this deadline into a string format suitable for file storage.
     * 
     * @return  A formatted string representing this deadline for saving to file
     */
    @Override
    public String toFileString() {
        String symbol = this.getDone() ? "1" : "0";
        return Type + " | " + symbol + " | " + getTask() + " | " + getDue();
    }
}