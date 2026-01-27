import java.util.Scanner;
import java.util.ArrayList;


public class Prime {
    public static String line = "________________________________________________";
    public static String greet = "I AM OPTIMUS PRIME.\nWhat can I do for you?";
    public static String bye = "Goodbye.";

    private static ArrayList<Todo> log = new ArrayList<>();

    // prints greeting when called
    public static void hello() {
        System.out.println(line);
        System.out.println(greet);
        System.out.println(line);
    }

    // prints goodbye when called
    public static void goodbye() {
        System.out.println(line);
        System.out.println(bye);
        System.out.println(line);
    }

    // adds item to log and repeats what has been added
    public static void add(String item) {
        log.add(new Todo(false, item));
        System.out.println(line);
        System.out.println("Added to list: " + item);
        System.out.println(line);
    }

    // prints out list
    public static void list() {
        int i = log.size();
        System.out.println(line);
        System.out.println("Here are your tasks currently in my log:");
        for (int a = 0; a < i; a++) {
            System.out.println((a+1)+ ". " + log.get(a).printTask());
        }
        System.out.println(line);
    }

    public static void mark(int index) {
        int i = index - 1;
        log.get(i).setDone(true);
        System.out.println(line);
        System.out.println("Great job on completing your task! I have marked it as done.");
        String message = log.get(i).printTask();
        System.out.println("    " + message);
        System.out.println(line);
    }

    public static void unmark(int index) {
        int i = index - 1;
        log.get(i).setDone(false);
        System.out.println(line);
        System.out.println("Alright, I'll mark it as not done.");
        String message = log.get(i).printTask();
        System.out.println("    " + message);
        System.out.println(line);
    }

    public static void main(String[] args) {
        hello();

        Scanner s = new Scanner(System.in);

        while (true) {
            System.out.print("Please, give me an instruction: ");
            String command = s.nextLine();
            
            if (command.isEmpty()) {
                System.out.println("Hmm.. you didn't say anything...");
            } else if (command.equalsIgnoreCase("bye")) {
                // stop the commands when "bye" is inputted
                goodbye();
                break;
            } else if (command.equalsIgnoreCase("list")) {
                // list the current items
                list();
            } else if (command.equalsIgnoreCase("mark")) {
                // input "mark" in the console, then input an integer when prompted
                int index = s.nextInt();
                s.nextLine(); // consume new line
                mark(index);
            } else if (command.equalsIgnoreCase("unmark")) {
                // input "unmark" in the console, then input an integer when prompted
                int index = s.nextInt();
                s.nextLine(); // consume new line
                unmark(index);
            } else {
                // else, add item to log
                add(command);
            }
        }

        s.close();
    }
}
