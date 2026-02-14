package prime.parser;

import java.time.LocalDate;
import java.util.ArrayList;

import prime.core.PrimeException;
import prime.task.Deadline;
import prime.task.Event;
import prime.task.Log;
import prime.task.NumberedTask;
import prime.task.ToDo;
import prime.ui.Ui;

/**
 * parses user input and executes the corresponding command
 * on the log
 */

public class PrimeParser {

    /**
     * marks a ToDo as done
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

            Ui.buildMessage("Great job on completing your task! I have marked it as done.");
            Ui.buildMessage("    " + task.printTask());
        } catch (NumberFormatException e) {
            throw new PrimeException("! : There should be a number after the 'mark' instruction.");
        }

    }

    /**
     * unmarks a ToDo -> marks a ToDo as undone
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

            Ui.buildMessage("Alright, I'll mark it as not done.");
            Ui.buildMessage("    " + task.printTask());

        } catch (NumberFormatException e) {
            throw new PrimeException("!! : Task number should be a number.");
        }

    }

    /**
     * adds a ToDo to log and prints what has been added
     *
     * @param item the ToDo description
     * @param log  the log to operate on
     */
    public static void addTodo(String item, Log log) {
        ToDo todo = new ToDo(false, item);
        log.add(todo);

        Ui.buildMessage("Understood. I have added to the log:");
        Ui.buildMessage("    " + todo.printTask());
    }

    /**
     * adds a Deadline to log and prints what has been added
     *
     * @param item    the deadline description
     * @param dueDate the due date of the deadline
     * @param log     the log to operate on
     * @throws PrimeException when the date inputted is in the wrong format
     */
    public static void addDeadline(String item, String dueDate, Log log) throws PrimeException {
        try {
            LocalDate by = LocalDate.parse(dueDate.trim());
            Deadline deadline = new Deadline(false, item, by);

            log.add(deadline);

            Ui.buildMessage("Understood. I have added to the log:");
            Ui.buildMessage("    " + deadline.printTask());

        } catch (Exception e) {
            throw new PrimeException("I cannot understand the date format. Please use YYYY-MM-DD.");
        }

    }

    /**
     * adds Event to log and prints what has been added
     *
     * @param item  the event description
     * @param start the start date of the deadline
     * @param end   the end date of the deadline
     * @param log   the log to operate on
     * @throws PrimeException when the date inputted is in the wrong format
     */
    public static void addEvent(String item, String start, String end, Log log) throws PrimeException {
        try {
            LocalDate from = LocalDate.parse(start.trim());
            LocalDate to = LocalDate.parse(end.trim());

            Event event = new Event(false, item, from, to);
            log.add(event);

            Ui.buildMessage("Understood. I have added to the log:");
            Ui.buildMessage("    " + event.printTask());

        } catch (Exception e) {
            throw new PrimeException("I cannot understand the date format. Please use YYYY-MM-DD.");
        }

    }

    /**
     * delete Todo from the log and repeats what was deleted
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

            Ui.buildMessage("Understood, this task has been deleted from the log:");
            Ui.buildMessage("    " + task.printTask());

        } catch (NumberFormatException e) {
            throw new PrimeException("!! : Task number should be a number.");
        }

    }

    /**
     * parses a user input and executes it
     *
     * @param input string input by the user
     * @param log   the log to operate on
     * @return false if user input is "bye" or "goodbye", true otherwise.
     *         signals if the program should continue running
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
                // stop the commands when "bye" is inputted
                // Ui.buildMessage("Goodbye, little one.");
                return false;

            case "list":
                // prints out list of current tasks
                Ui.buildMessage("You have " + log.size() + " tasks. Let's keep at it!",
                        "Here are your tasks currently in my log:" );
                Ui.printArrayList(log.getAll());

                return true;

            case "mark":
                mark(rest, log);
                return true;

            case "unmark":
                unmark(rest, log);
                return true;

            case "find":
                if (rest.isEmpty()) {
                    throw new PrimeException("!! : Please provide me a search keyword...");
                }
                ArrayList<NumberedTask> result = log.find(rest);
                Ui.showFindResults(result);
                return true;

            case "delete":
            case "remove":
                delete(rest, log);
                Ui.buildMessage("You have " + log.size() + " tasks. Let's keep at it!");
                return true;

            case "todo":
                // input is a todo
                // todos need task descriptions
                if (rest.isEmpty()) {
                    throw new PrimeException("!! : Please provide me a description of your task.");
                }

                addTodo(rest, log);
                Ui.buildMessage("You have " + log.size() + " tasks. Let's keep at it!");
                return true;

            case "deadline":
                // input is a deadline
                // deadlines need task descriptions and a deadline
                if (rest.isEmpty()) {
                    throw new PrimeException("!! : I am sorry, but this instruction is incomplete.");
                }

                String[] dueStrings = rest.split("/by ", 2);
                if (dueStrings.length != 2) {
                    throw new PrimeException("!! : Please provide me with the task description"
                            + " and the due date in YYYY-MM-DD.");
                }

                addDeadline(dueStrings[0], dueStrings[1], log);
                Ui.buildMessage("You have " + log.size() + " tasks. Let's keep at it!");
                return true;

            case "event":
                // input is a event
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
                if (dates.length != 2) {
                    throw new PrimeException("!! : Are you missing the start or end times?"
                            + " They must be in the correct order.");
                }

                addEvent(timingsArray[0].trim(), dates[0], dates[1], log);

                Ui.buildMessage("You have " + log.size() + " tasks. Let's keep at it!");
                return true;

            default:
                throw new PrimeException("!! : I am sorry, I do not recognise this instruction...");
        }
    }

}
