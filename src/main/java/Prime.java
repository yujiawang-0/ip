import java.util.Scanner;

public class Prime {
    private static final Log log = new Log();
    private static Storage storage;

    public static void main(String[] args) {
        try {
            storage = new Storage();

            // Load saved tasks
            for (ToDo task : storage.loadData()) {
                log.add(task);
            }

            Ui.showHello();
            Ui.showPrime();

            Scanner s = new Scanner(System.in);
            boolean isRunning = true;

            while (isRunning) {
                try {
                    Ui.showMessage("Please, give me an instruction: ");
                    String input = s.nextLine().trim();
                    Ui.showLine();

                    isRunning = PrimeParser.parse(input, log);

                    // save the data after every command by the user
                    storage.saveData(log.getAll());

                    Ui.showLine();

                } catch (PrimeException e) {
                    Ui.showError(e.getMessage());
                    Ui.showLine();
                }
            }

            s.close();

        } catch (Exception e) {
            Ui.showError(e.getMessage());
        }
        
    }
}
