package prime.task;

/**
 * Represents a task together with its index in the log
 * It is a helper class for finding method in log
 */
public class NumberedTask {
    private final int index;
    private final ToDo task;

    /**
     * Constructor for NumberedTask
     * @param index represents the index of the task in the log
     * @param task represents the task
     */
    public NumberedTask(int index, ToDo task) {
        this.index = index;
        this.task = task;
    }

    public int getIndex() {
        return this.index;
    }

    public ToDo getTask() {
        return this.task;
    }
}
