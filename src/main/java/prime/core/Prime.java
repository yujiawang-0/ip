package prime.core;

import prime.parser.PrimeParser;
import prime.storage.Storage;
import prime.task.Log;
import prime.task.ToDo;
import prime.ui.Ui;

/**
 * Main class / entry point for the program
 * Starts the scanner for user input
 * initalises log and storage to be used by the rest of the program
 */
public class Prime {
    private static final Log log = new Log();
    private static Storage storage;

    public Prime() {
        try {
            storage = new Storage();

            for (ToDo task : storage.loadData()) {
                log.add(task);
            }

            assert log != null : "Log should be initialised";
            assert storage != null : "Storage should be initialised";

        } catch (Exception e) {
            // If loading fails, GUI will show error on first command
            Ui.buildError("Error loading storage: " + e.getMessage());
        }
    }

    /**
     * Processes user input and returns the response as a String.
     */
    public String getResponse(String input) {
        try {
            boolean isRunning = PrimeParser.parse(input, log);

            // Save after every successful command
            storage.saveData(log.getAll());

            if (!isRunning) {
                return "__EXIT__"; // signal to the GUI to close
            }

            return Ui.getResponse();

        } catch (PrimeException e) {
            return e.getMessage();

        } catch (Exception e) {
            return "Unexpected error: " + e.getMessage();
        }
    }

    public String getGreeting() {
        Ui.buildMessage("I AM OPTIMUS PRIME.\nHello, little one.");
        return Ui.getResponse();
    }


    // public static void main(String[] args) {
    //     try {
    //         storage = new Storage();

    //         // Load saved tasks
    //         for (ToDo task : storage.loadData()) {
    //             log.add(task);
    //         }

    //         Ui.showHello();
    //         // Ui.showPrime();

    //         Scanner s = new Scanner(System.in);
    //         boolean isRunning = true;

    //         while (isRunning) {
    //             try {
    //                 Ui.buildMessage("Please, give me an instruction: ");
    //                 String input = s.nextLine().trim();
    //                 Ui.showLine();

    //                 isRunning = PrimeParser.parse(input, log);

    //                 // save the data after every command by the user
    //                 storage.saveData(log.getAll());

    //                 Ui.showLine();

    //             } catch (PrimeException e) {
    //                 Ui.buildError(e.getMessage());
    //                 Ui.showLine();
    //             }
    //         }

    //         s.close();

    //     } catch (Exception e) {
    //         Ui.buildError(e.getMessage());
    //     }

    // }
}
