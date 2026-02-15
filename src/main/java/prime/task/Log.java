package prime.task;

import java.util.ArrayList;

import prime.core.PrimeException;

/**
 * Stores and manages the list of tasks in Prime.
 *
 * The log keeps track of all task types, including todos, deadlines, and events.
 */
public class Log {
    private ArrayList<ToDo> log = new ArrayList<>();

    /**
     * Adds a task to the log
     * @param task  task to be added to the log
     */
    public void add(ToDo task) {
        if (task == null) {
            throw new IllegalArgumentException("!! : Task cannot be null");
        }
        log.add(task);

        assert log.size() > 0 : "Log should not be empty after adding a task";
    }

    /**
     * Removes a task from the log by its index.
     *
     * @param index The index of the task to remove (0-based).
     * @throws PrimeException If the index is invalid.
     */
    public void delete(int index) throws PrimeException {
        if (index < 0 || index >= log.size()) {
            throw new PrimeException("I'm sorry, but that task does not exist.");
        }
        log.remove(index);
    }

    /**
     * Retrieves a task from the log by its index.
     *
     * @param index The index of the task to retrieve (0-based).
     * @return The task at the specified index.
     * @throws PrimeException If the index is invalid.
     */
    public ToDo get(int index) throws PrimeException {
        if (index < 0 || index >= log.size()) {
            throw new PrimeException("I'm sorry, but that task does not exist.");
        }
        return log.get(index);
    }

    public int size() {
        return log.size();
    }

    public ArrayList<ToDo> getAll() {
        return new ArrayList<>(log); // ensure that the original log is immutable
    }

    /**
     * Given a keyword, find all tasks in the log that match the keyword
     *
     * @param keyword
     * @return all tasks in the log that match the keyword
     */
    public ArrayList<NumberedTask> find(String keyword) {
        ArrayList<NumberedTask> results = new ArrayList<>();
        if (keyword == null || keyword.trim().isEmpty()) {
            return results;
        }
        
        String lowercase = keyword.toLowerCase();

        for (int i = 0; i < log.size(); i++) {
            ToDo task = log.get(i);
            if (task.getTask().toLowerCase().contains(lowercase)) {
                results.add(new NumberedTask(i, task));
            }
        }

        return results;
    }

}
