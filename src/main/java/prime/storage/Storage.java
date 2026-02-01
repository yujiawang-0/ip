package prime.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import prime.core.PrimeException;
import prime.task.Deadline;
import prime.task.Event;
import prime.task.ToDo;

import java.time.LocalDate;

/**
 * handles persistent storage of tasks by reading from and writing to a local file.
 * 
 * tasks are saved in a .txt file and reconstructed when the application
 * starts, allowing task data to persist when prime stops and starts.
 */
public class Storage {
    private static final String FILE_PATH = "data/prime.txt";

    /**
     * ensure that data file exists and creates a prime.txt storage file under /data folder
     * 
     * @throws PrimeException   if the storage file cannot be created or accessed
     */
    public Storage() throws PrimeException {
        try {
            File file = new File(FILE_PATH);
            file.getParentFile().mkdir(); // create ./data if missing
            file.createNewFile(); // create the storage file if missing
        } catch (IOException e) {
            throw new PrimeException("I am unable to initialise storage:" + e.getMessage());
        }
    }

    /**
     * saves the current list to the storage file
     * 
     * @param log   list of tasks to be stored
     * @throws PrimeException   if an error occurs while writing to the file
     */
    public void saveData(ArrayList<ToDo> log) throws PrimeException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (ToDo task : log) {
                bw.write(task.toFileString());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new PrimeException("Apologies, there is an error in saving log:" + e.getMessage());
        }
    }

    /**
     * load tasks from the storage file and reconstructs them into an ArrayList
     * 
     * @return  an ArrayList of ToDos
     * @throws PrimeException   if an error occurs while reading or parsing the file
     */
    public ArrayList<ToDo> loadData() throws PrimeException {
        ArrayList<ToDo> tasks = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                tasks.add(parseLine(line));
            }
        } catch (IOException e) {
            throw new PrimeException("Apologies, there is an error reading data:" + e.getMessage());
        }

        return tasks;
    }

    /**
     * parses a single line from the storage file and converts it into a task object.
     * 
     * @param line  a line from the data file
     * @return  the reconstructed task in a ToDo
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
                return new Deadline(isDone, parts[2].trim(), LocalDate.parse(parts[3].trim()));

            case "E":
                return new Event(isDone, parts[2], LocalDate.parse(parts[3].trim()),
                        LocalDate.parse(parts[4].trim()));

            default:
                throw new PrimeException("There seems to be a corrupted data file...");
            }
        } catch (Exception e) {
            throw new PrimeException("There seems to be a corrupted data file...");
        }

    }

}
