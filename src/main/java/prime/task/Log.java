package prime.task;

// keeps tracks of the tasks (todos, events, deadlines) in the log
import java.util.ArrayList;

import prime.core.PrimeException;

/**
 * Stores and manages the list of tasks in Prime.
 * 
 * The log keeps track of all task types, including todos,
 * deadlines, and events.
 */
public class Log {
    private ArrayList<ToDo> log = new ArrayList<>();

    public void add(ToDo task) {
        log.add(task);
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
        return log;
    }

}
