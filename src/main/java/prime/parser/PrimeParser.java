package prime.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import prime.core.PrimeException;
import prime.task.Deadline;
import prime.task.Event;
import prime.task.Log;
import prime.task.NumberedTask;
import prime.task.ToDo;
import prime.ui.Ui;

/**
 * Parses user input and executes the corresponding command on the log
 */
public class PrimeParser {

    private static void showTaskCount(Log log) {
        Ui.buildMessage("You have " + log.size() + " tasks. Let's keep at it!");
    }

    /**
     * Marks a ToDo as done
     * Does not check whether a ToDo was done or not
     *
     * @param rest the rest of the instruction, which should a number in the form of
     *             a string
     * @param log  the log to operate on
     * @throws PrimeException if the instruction is invalid
     */
    public static void mark(String rest, Log log) throws PrimeException {
        if (rest.isEmpty()) {
            throw new PrimeException("!! : Please tell me which task to mark.");
        }

        try {
            int index = Integer.parseInt(rest) - 1;
            ToDo task = log.get(index);
            task.setDone(true);

            Ui.buildMessage("Great job on completing your task! I have marked it as done.",
                    "    " + task.printTask());

        } catch (NumberFormatException e) {
            throw new PrimeException("! : There should be a number after the 'mark' instruction.");
        }

    }

    /**
     * Unmarks a ToDo -> marks a ToDo as undone
     * Does not check whether a ToDo is done or not
     *
     * @param rest the rest of the instruction, which should a number in the form of
     *             a string
     * @param log  the log to operate on
     * @throws PrimeException if the instruction is invalid
     */
    public static void unmark(String rest, Log log) throws PrimeException {
        if (rest.isEmpty()) {
            throw new PrimeException("!! : Please tell me which task to unmark.");
        }

        try {
            int index = Integer.parseInt(rest) - 1;
            ToDo task = log.get(index);
            task.setDone(false);

            Ui.buildMessage("Alright, I'll mark it as not done.",
                    "    " + task.printTask());

        } catch (NumberFormatException e) {
            throw new PrimeException("!! : Task number should be a number.");
        }

    }

    /**
     * Adds a ToDo to log and prints what has been added
     *
     * @param item the ToDo description
     * @param log  the log to operate on
     */
    public static void addToDo(String item, Log log) throws PrimeException {
        // input is a todo
        // todos need task descriptions
        if (item.trim().isEmpty()) {
            throw new PrimeException("!! : Please provide me a description of your task.");
        }

        ToDo todo = new ToDo(false, item);
        log.add(todo);

        Ui.buildMessage("Understood. I have added to the log:",
                "    " + todo.printTask());
        showTaskCount(log);
    }

    // helper for addDeadline : handles parsing logic
    private static Deadline parseDeadline(String rest) throws PrimeException {
        String[] split = rest.split("/by ", 2);

        if (split.length != 2) {
            throw new PrimeException(
                    "!! : Please provide me the task description and due date in YYYY-MM-DD.");
        }

        String description = split[0].trim();
        String dateString = split[1].trim();

        try {
            LocalDate dueDate = LocalDate.parse(dateString);
            return new Deadline(false, description, dueDate);

        } catch (IllegalArgumentException e) {
            throw new PrimeException(e.getMessage());
        } catch (DateTimeParseException e) {
            throw new PrimeException("I cannot understand the date format. Please use YYYY-MM-DD.");
        }
    }

    /**
     * Adds a Deadline to log and prints what has been added
     *
     * @param rest  the rest of the instructions
     * @param log  the log to operate on
     * @throws PrimeException when the date inputted is in the wrong format
     */
    public static void addDeadline(String rest, Log log) throws PrimeException {
        // deadlines need task descriptions and a deadline
        if (rest.isEmpty()) {
            throw new PrimeException("!! : I am sorry, but this instruction is incomplete.");
        }

        Deadline deadline = parseDeadline(rest);
        log.add(deadline);


        Ui.buildMessage("Understood. I have added to the log:",
                "    " + deadline.printTask());
        showTaskCount(log);
    }

    // helper for addEvent: handles parsing logic
    private static Event parseEvent(String rest) throws PrimeException {
        String[] descriptionSplit = rest.split("/from ", 2);

        if (descriptionSplit.length != 2) {
            throw new PrimeException("!! : Please provide task description, start and end times.");
        }

        String description = descriptionSplit[0].trim();

        String[] dateSplit = descriptionSplit[1].split("/to ", 2);
        if (dateSplit.length != 2) {
            throw new PrimeException("!! : Start and end times must follow /from and /to format.");
        }

        try {
            LocalDate startDate = LocalDate.parse(dateSplit[0].trim());
            LocalDate endDate = LocalDate.parse(dateSplit[1].trim());

            return new Event(false, description, startDate, endDate);

        } catch (DateTimeParseException e) {
            throw new PrimeException("I cannot understand the date format. Please use YYYY-MM-DD.");
        }
    }

    /**
     * Adds Event to log and prints what has been added
     *
     * @param rest the rest of the instructions
     * @param log  the log to operate on
     * @throws PrimeException when the date inputted is in the wrong format
     */
    public static void addEvent(String rest, Log log) throws PrimeException {
        // events need task desc, start and end time
        if (rest.isEmpty()) {
            throw new PrimeException("!! : I am sorry, but this instruction is incomplete.");
        }

        Event event;
        try {
            event = parseEvent(rest);
        } catch (IllegalArgumentException e) {
            throw new PrimeException(e.getMessage());
        }
        log.add(event);

        Ui.buildMessage("Understood. I have added to the log:", "    " + event.printTask());
        showTaskCount(log);

        assert log.size() > 0 : "Log should contain at least one task after adding event";
    }

    /**
     * Delete Todo from the log and repeats what was deleted
     *
     * @param rest the rest of the instruction, which should a number in the form of
     *             a string
     * @param log  the log to operate on
     * @throws PrimeException if the instruction is invalid
     */
    public static void delete(String rest, Log log) throws PrimeException {
        if (rest.isEmpty()) {
            throw new PrimeException("!! : Please tell me which task to delete.");
        }

        try {
            int index = Integer.parseInt(rest) - 1;
            ToDo task = log.get(index);
            log.delete(index);

            Ui.buildMessage("Understood, this task has been deleted from the log:",
                    "    " + task.printTask());

        } catch (NumberFormatException e) {
            throw new PrimeException("!! : Task number should be a number.");
        }

        showTaskCount(log);
    }

    /**
     * Updates the specific field that the user wants to update with the newValue
     * Only able to update one field of one task at one time.
     */
    public static void update(String rest, Log log) throws PrimeException {
        String[] parts = rest.split("\\s+", 3);
        if (parts.length < 3) {
            throw new PrimeException("!! : Usage: update <index> <field> <newValue>");
        }

        int index;
        try {
            index = Integer.parseInt(parts[0]) - 1;
        } catch (NumberFormatException e) {
            throw new PrimeException("!! : Index must be a number.");
        }

        String field = parts[1].toLowerCase();
        String newValue = parts[2].trim();

        ToDo task = log.get(index);

        task.updateField(field, newValue);

        Ui.buildMessage("Understood. I updated the task:", "    " + task.printTask());

    }

    /**
     * Lists all the current tasks in the Log
     * @param log
     */
    public static void list(Log log) {
        // prints out list of current tasks
        showTaskCount(log);
        Ui.buildMessage("Here are your tasks currently in my log:");
        Ui.printArrayList(log.getAll());
    }

    /**
     * Finds all the tasks matching the keyword
     *
     * @param rest the keyword
     * @param log  the log to operate on
     * @throws PrimeException
     */
    public static void find(String rest, Log log) throws PrimeException {
        if (rest.trim().isEmpty()) {
            throw new PrimeException("!! : Please provide me a search keyword...");
        }
        ArrayList<NumberedTask> result = log.find(rest);
        Ui.showFindResults(result);
    }

    /**
     * Parses a user input, executes it, and provides a string to print in the GUI
     *
     * @param input string input by the user
     * @param log   the log to operate on
     * @return      false if user input is "bye" or "goodbye", true otherwise.
     *              signals if the program should continue running
     * @throws PrimeException if the command is invalid
     */
    public static boolean parse(String input, Log log) throws PrimeException {
        input = input.trim(); // Prime.java already trims, but parser should also do its own trimming
        if (input.isEmpty()) {
            throw new PrimeException("!! : Hmm.. you didn't say anything...");
        }

        String[] parts = input.split("\\s+", 2);
        String command = parts[0].toLowerCase();
        String rest = parts.length > 1 ? parts[1] : "";

        switch (command) {
        case "hello":
        case "hi":
            // says hello again
            Ui.buildMessage("Hello, child.");
            return true;

        case "bye":
        case "goodbye":
            // stop the program when "bye" is inputted
            // goodbye string is handled by the MainWindow
            return false;

        case "list":
            list(log);
            return true;

        case "mark":
            mark(rest, log);
            return true;

        case "unmark":
            unmark(rest, log);
            return true;

        case "find":
            find(rest, log);
            return true;

        case "delete":
        case "remove":
            delete(rest, log);

            return true;

        case "todo":
            addToDo(rest, log);
            return true;

        case "deadline":
            addDeadline(rest, log);
            return true;

        case "event":
            addEvent(rest, log);
            return true;

        case "update":
        case "edit":
            update(rest, log);
            return true;

        default:
            throw new PrimeException("!! : I am sorry, I do not recognise this instruction...");
        }
    }

}
