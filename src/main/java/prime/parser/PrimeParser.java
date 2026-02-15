package prime.parser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.time.format.DateTimeParseException;

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
        if (item.isEmpty()) {
            throw new PrimeException("!! : Please provide me a description of your task.");
        }

        ToDo todo = new ToDo(false, item);
        log.add(todo);

        Ui.buildMessage("Understood. I have added to the log:",
                "    " + todo.printTask());
        showTaskCount(log);
    }

    /**
     * Adds a Deadline to log and prints what has been added
     *
     * @param item    the deadline description
     * @param dueDate the due date of the deadline
     * @param log     the log to operate on
     * @throws PrimeException when the date inputted is in the wrong format
     */
    public static void addDeadline(String rest, Log log) throws PrimeException {
        // deadlines need task descriptions and a deadline
        if (rest.isEmpty()) {
            throw new PrimeException("!! : I am sorry, but this instruction is incomplete.");
        }

        String[] dueStrings = rest.split("/by ", 2);
        if (dueStrings.length != 2) {
            throw new PrimeException("!! : Please provide me with the task description"
                    + " and the due date in YYYY-MM-DD.");
        }

        String item = dueStrings[0];
        String dueDate = dueStrings[1];

        try {
            LocalDate by = LocalDate.parse(dueDate.trim());
            Deadline deadline = new Deadline(false, item, by);

            log.add(deadline);

            Ui.buildMessage("Understood. I have added to the log:",
                    "    " + deadline.printTask());

        } catch (DateTimeParseException e) {
            throw new PrimeException("I cannot understand the date format. Please use YYYY-MM-DD.");
        }

        showTaskCount(log);
    }


    /**
     * Adds Event to log and prints what has been added
     *
     * @param item  the event description
     * @param start the start date of the deadline
     * @param end   the end date of the deadline
     * @param log   the log to operate on
     * @throws PrimeException when the date inputted is in the wrong format
     */
    public static void addEvent(String rest, Log log) throws PrimeException {
        // events need task desc, start and end time
        if (rest.isEmpty()) {
            throw new PrimeException("!! : I am sorry, but this instruction is incomplete.");
        }

        String[] timingsArray = rest.split("/from ", 2);
        if (timingsArray.length != 2) {
            throw new PrimeException("!! : Please provide me with the task description, "
                    + "start and end times.");
        }

        String timings = timingsArray[1];
        String[] dates = timings.split("/to ", 2);
        if(dates.length != 2) {
            throw new PrimeException("!! : Are you missing the start or end times?"
                    + " They must be in the correct order.");
        }

        String item = timingsArray[0].trim();
        String start = dates[0];
        String end = dates[1];

        try {
            LocalDate from = LocalDate.parse(start.trim());
            LocalDate to = LocalDate.parse(end.trim());

            Event event = new Event(false, item, from, to);
            log.add(event);

            Ui.buildMessage("Understood. I have added to the log:", "    " + event.printTask());

        } catch (DateTimeParseException e) {
            throw new PrimeException("I cannot understand the date format. Please use YYYY-MM-DD.");
        }

        showTaskCount(log);

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
     * Lists all the current tasks in the Log
     * @param log
     */
    public static void list(Log log) {
        // prints out list of current tasks
        showTaskCount(log);
        Ui.buildMessage("Here are your tasks currently in my log:" );
        Ui.printArrayList(log.getAll());
    }

    /**
     * Finds all the tasks matching the keyword
     * @param rest  the keyword
     * @param log
     * @throws PrimeException
     */
    public static void find(String rest, Log log) throws PrimeException {
        if (rest.isEmpty()) {
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

        default:
            throw new PrimeException("!! : I am sorry, I do not recognise this instruction...");
        }
    }

}
