package prime.ui;

import java.util.ArrayList;

import prime.task.NumberedTask;
import prime.task.ToDo;

/**
 * Ui class handles all the printing in the CLI
 */
public class Ui {

    private static StringBuilder response = new StringBuilder();

    /**
     * Builds message to be passed to the GUI
     *
     * @param messages is the message to be printed
     */
    public static void buildMessage(String... messages) {
        for (String message : messages) {
            response.append(message).append("\n");
        }
    }

    /**
     * Builds error message to be passed into the GUI
     *
     * @param error
     */
    public static void buildError(String error) {
        response.append(error).append("\n");
    }

    /**
     * Get response for GUI to print
     * @return string to be printed
     */
    public static String getResponse() {
        String result = response.toString();
        response.setLength(0); // clear the result after reading
        return result;
    }

    /**
     * Prints log
     *
     * @param log
     */
    public static void printArrayList(ArrayList<ToDo> log) {
        int i = 1;
        for (ToDo task : log) {
            response.append(i)
                    .append(". ")
                    .append(task.printTask())
                    .append("\n");

            i++;
        }
    }

    /**
     * Prints items found during the find command
     *
     * @param matches tasks that matches the word to be found
     */
    public static void showFindResults(ArrayList<NumberedTask> matches) {
        if (matches.isEmpty()) {
            buildMessage("There are no tasks that match your search...");
        } else {
            buildMessage("Here are the matching tasks in your list:");

            for (NumberedTask match : matches) {
                response.append(match.getIndex() + 1)
                        .append(". ")
                        .append(match.getTask().printTask())
                        .append("\n");
            }
        }

    }

}
