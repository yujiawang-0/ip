// keeps tracks of the tasks (todos, events, deadlines) in the log
import java.util.ArrayList;

public class Log {
    private static ArrayList<ToDo> log = new ArrayList<>();
    
    public void add(ToDo task) {
        log.add(task);
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

    // returns the number of tasks in the log
    public void printLogSize() {
        String size = String.valueOf(log.size());
        System.out.println("You have " + size + " tasks. Let's keep at it!");
    }

}
