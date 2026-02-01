package prime.task;

/**
 * represents a task together with its index in the log
 * helper class for finding method in log
 */
public class NumberedTask {
    private final int index;
    private final ToDo task;

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
