package prime.task;
// keeps tracks of the tasks (todos, events, deadlines) in the log
import java.util.ArrayList;

import prime.core.PrimeException;

public class Log {
    private ArrayList<ToDo> log = new ArrayList<>();
    
    public void add(ToDo task) {
        log.add(task);
    }

    public void delete(int index) throws PrimeException {
        if (index < 0 || index >= log.size()) {
            throw new PrimeException("I'm sorry, but that task does not exist.");
        }
        log.remove(index);
    }

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
