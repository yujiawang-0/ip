package prime.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import prime.core.PrimeException;
import prime.task.Deadline;
import prime.task.Event;
import prime.task.ToDo;


/**
 * Handles persistent storage of tasks by reading from and writing to a local file.
 *
 * Tasks are saved in a .txt file and reconstructed when the application
 * starts, allowing task data to persist when prime stops and starts.
 */
public class Storage {
    private static final String FILE_PATH = "./data/prime.txt";

    /**
     * Constructor for creating Storage item
     * Ensures that data file exists and creates a prime.txt storage file under /data folder
     * @throws PrimeException   if the storage file cannot be created or accessed
     */
    public Storage() throws PrimeException {
        try {
            File file = new File(FILE_PATH);
            file.getParentFile().mkdirs(); // create ./data if missing
            file.createNewFile(); // create the storage file if missing
        } catch (IOException e) {
            throw new PrimeException("I am unable to initialise storage: " + e.getMessage());
        }
    }

    /**
     * Saves the current list to the storage file
     *
     * @param log               list of tasks to be stored
     * @throws PrimeException   if an error occurs while writing to the file
     */
    public void saveData(ArrayList<ToDo> log) throws PrimeException {
        File file = new File(FILE_PATH);

        // check if parent directory exists
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (ToDo task : log) {
                bw.write(task.toFileString());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new PrimeException("Apologies, there is an error in saving log: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the storage file and reconstructs them into an ArrayList
     *
     * @return                  an ArrayList of ToDos
     * @throws PrimeException   if an error occurs while reading or parsing the file
     */
    public ArrayList<ToDo> loadData() throws PrimeException {
        File file = new File(FILE_PATH);
        ArrayList<ToDo> tasks = new ArrayList<>();

        // Create directory if it doesn't exist
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        // create storage file if it doesn't exist
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new PrimeException("Unable to create storage file: " + e.getMessage());
            }
            return tasks;
        }

        try {
            processFileData(file, tasks);
        } catch (IOException e) {
            throw new PrimeException("Apologies, there is an error reading data: " + e.getMessage());
        }

        return tasks;
    }

    // @@author eejon--reused
    // Reused from https://github.com/eejon/ip/tree/master
    // with minor modifications
    private ArrayList<ToDo> processFileData(File inputFile, ArrayList<ToDo> tasks) throws IOException {
        FileReader fileIn = new FileReader(inputFile);
        BufferedReader bufferIn = new BufferedReader(fileIn);

        String line;
        int lineNumber = 1;
        while ((line = bufferIn.readLine()) != null) {
            try {
                ToDo task = parseLine(line.trim());
                tasks.add(task);
            } catch (PrimeException e) {
                // Log corrupted line but continue loading other tasks
                System.err.println("\t !! : Error(s) on "
                        + lineNumber + ": " + e.getMessage() + " --- skipped.");
            } finally {
                lineNumber++;
            }
        }
        bufferIn.close();
        return tasks;
    }

    /**
     * Parses a single line from the storage file and converts it into a task object.
     *
     * @param line              a line from the data file
     * @return                  the reconstructed task in a ToDo
     * @throws PrimeException   If the data format is not as expected
     */
    private ToDo parseLine(String line) throws PrimeException {
        try {
            String[] parts = line.split(" \\| ");
            boolean isDone = parts[1].equals("1");

            switch (parts[0]) {
            case "T":
                return new ToDo(isDone, parts[2]);

            case "D":
                if (parts.length != 4) {
                    throw new PrimeException("Corrupted deadline entry.");
                }
                return new Deadline(isDone, parts[2].trim(), LocalDate.parse(parts[3].trim()));

            case "E":
                if (parts.length != 5) {
                    throw new PrimeException("Corrupted event entry.");
                }
                return new Event(isDone, parts[2], LocalDate.parse(parts[3].trim()),
                        LocalDate.parse(parts[4].trim()));

            default:
                throw new PrimeException("Unknown task type encountered.");
            }
        } catch (Exception e) {
            throw new PrimeException("There seems to be a corrupted data file...");
        }

    }

}
