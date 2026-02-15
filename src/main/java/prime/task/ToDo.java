package prime.task;

import prime.core.PrimeException;

/**
 * Represents a basic to-do task without any associated date or time.
 *
 * A {@code ToDo} task contains a description and a completion status.
 */
public class ToDo {
    private static final String TYPE = "T";
    private boolean isDone = false;
    private String task;

    /**
     * Constructs a to-do task.
     *
     * @param isDone Whether the task is initially marked as completed.
     * @param task   The description of the task.
     */
    public ToDo(boolean isDone, String task) {
        setDone(isDone);
        setTask(task);
    }

    // getter for completion
    public boolean isDone() {
        return this.isDone;
    }

    // setter for completion
    public void setDone(boolean isCompleted) {
        this.isDone = isCompleted;
    }

    // getter for task
    public String getTask() {
        return this.task;
    }

    // setter for task
    public void setTask(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("!! : Task description cannot be empty");
        }
        this.task = input.trim();
    }

    /**
     * Returns a user-friendly string representation of the to-do task.
     *
     * @return The formatted task string for display.
     */
    public String printTask() {
        String symbol = this.isDone() ? "X" : " ";
        return "[" + TYPE + "]" + "[" + symbol + "] " + getTask();
    }

    /**
     * Converts the task into a string format suitable for file storage.
     *
     * @return A formatted string representing this task for saving to file.
     */
    public String toFileString() {
        String symbol = this.isDone() ? "1" : "0";
        return TYPE + " | " + symbol + " | " + getTask();
    }

    /**
     * Updates the fields in ToDos
     * @param field             to be updated
     * @param newValue          new value to replace the old value
     * @throws PrimeException   if this field does not exist in this type of task
     */
    public void updateField(String field, String newValue) throws PrimeException {
        switch (field) {
        case "desc":
            setTask(newValue);
            break;

        default:
            throw new PrimeException("!! : Invalid field for ToDo type");
        }
    }

}
