package prime.task;

import java.util.ArrayList;

import prime.core.PrimeException;

// keeps tracks of the tasks (todos, events, deadlines) in the log
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

    public ArrayList<NumberedTask> find(String keyword) {
        ArrayList<NumberedTask> results = new ArrayList<>();
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
