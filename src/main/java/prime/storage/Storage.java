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

public class Storage {
    private static final String FILE_PATH = "data/prime.txt";

    public Storage() throws PrimeException{
        try {
            File file = new File(FILE_PATH);
            file.getParentFile().mkdir(); // create ./data if missing
            file.createNewFile();   // create the storage file if missing
        } catch (IOException e) {
            throw new PrimeException("I am unable to initialise storage:" + e.getMessage());
        }
    }

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


    public ArrayList<ToDo> loadData() throws PrimeException{
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


    private ToDo parseLine(String line) throws PrimeException{
        try {
            String[] parts = line.split(" \\| ");
            boolean isDone = parts[1].equals("1");

            switch (parts[0]) {
            case "T":
                return new ToDo(isDone, parts[2]);
            
            case "D":
                return new Deadline(isDone, parts[2].trim(), LocalDate.parse(parts[3].trim()));
            
            case "E":
                return new Event(isDone, parts[2], LocalDate.parse(parts[3].trim()), LocalDate.parse(parts[4].trim()));    

            default:
                throw new PrimeException("There seems to be a corrupted data file...");
            }
        } catch (Exception e) {
            throw new PrimeException("There seems to be a corrupted data file...");
        }
        
    }

}



// public class FilesClassDemo {

//     public static void main(String[] args) throws IOException{
//         Files.copy(Paths.get("data/fruits.txt"), Paths.get("temp/fruits2.txt"));
//         Files.delete(Paths.get("temp/fruits2.txt"));
//     }

// }


// public class FileWritingDemo {

//     private static void writeToFile(String filePath, String textToAdd) throws IOException {
//         FileWriter fw = new FileWriter(filePath);
//         fw.write(textToAdd);
//         fw.close();
//     }

//     public static void main(String[] args) {
//         String file2 = "temp/lines.txt";
//         try {
//             writeToFile(file2, "first line" + System.lineSeparator() + "second line");
//         } catch (IOException e) {
//             System.out.println("Something went wrong: " + e.getMessage());
//         }
//     }

// }