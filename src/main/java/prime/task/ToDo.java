package prime.task;

/**
 * Represents a basic to-do task without any associated date or time.
 * 
 * A {@code ToDo} task contains a description and a completion status.
 */
public class ToDo {
    private String Type = "T";
    private boolean isDone = false;
    private String Task;

    /**
     * Constructs a to-do task.
     *
     * @param isDone Whether the task is initially marked as completed.
     * @param Task   The description of the task.
     */
    public ToDo(boolean isDone, String Task) {
        setDone(isDone);
        setTask(Task);
    }

    // getter for completion
    public boolean getDone() {
        return this.isDone;
    }

    // setter for completion
    public void setDone(boolean isCompleted) {
        this.isDone = isCompleted;
    }

    // getter for task
    public String getTask() {
        return this.Task;
    }

    // setter for task
    public void setTask(String input) {
        this.Task = input;
    }

    /**
     * Returns a user-friendly string representation of the to-do task.
     *
     * @return The formatted task string for display.
     */
    public String printTask() {
        String symbol = this.getDone() ? "X" : " ";
        return "[" + Type + "]" + "[" + symbol + "] " + getTask();
    }

    /**
     * Converts the task into a string format suitable for file storage.
     *
     * @return A formatted string representing this task for saving to file.
     */
    public String toFileString() {
        String symbol = this.getDone() ? "1" : "0";
        return Type + " | " + symbol + " | " + getTask();
    }

}
