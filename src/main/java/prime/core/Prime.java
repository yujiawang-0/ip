package prime.core;

import prime.parser.PrimeParser;
import prime.storage.Storage;
import prime.task.Log;
import prime.task.ToDo;
import prime.ui.Ui;

/**
 * Main class / entry point for the program
 * Initalises log and storage to be used by the rest of the program
 */
public class Prime {
    private final Log log;
    private final Storage storage;

    /**
     * Constructor to create a Prime object
     */
    public Prime() {
        log = new Log();
        try {
            storage = new Storage();
        } catch (PrimeException e) {
            // should not ever have to hit here but just in case
            Ui.buildError("Unable to initialise storage:\n" + e.getMessage()
                    + "\nPlease relaunch PRIME using:\njava -jar PRIME.jar");
            javafx.application.Platform.exit(); // for GUI to close
            throw new RuntimeException("Storage initialisation failed.", e);
        }

        assert log != null : "Log should be initialised";
        assert storage != null : "Storage should be initialised";

        try {
            for (ToDo task : storage.loadData()) {
                log.add(task);
            }

        } catch (Exception e) {
            // If loading fails, GUI will show error on first command
            Ui.buildError("Error loading storage: " + e.getMessage());
        }
    }

    /**
     * Processes user input and returns the response as a String for the GUI to output
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

    /**
     * Sends the text needed for greeting in the GUI
     * @return Prime's greeting string
     */
    public String getGreeting() {
        Ui.buildMessage("I AM OPTIMUS PRIME.\nHello, little one.");
        return Ui.getResponse();
    }

}
