import java.util.Scanner;
import java.util.ArrayList;


public class Prime {
    public static String line = "________________________________________________";
    public static String greet = "I AM OPTIMUS PRIME.\nWhat can I do for you?";
    public static String bye = "Goodbye.";

    private static ArrayList<String> log = new ArrayList<>();

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
        log.add(item);
        System.out.println(line);
        System.out.println("Added to list: " + item);
        System.out.println(line);
    }

    // prints out list
    public static void list() {
        int i = log.size();
        System.out.println(line);
        for (int a = 0; a < i; a++) {
            System.out.println((a+1)+ ". " + log.get(a));
        }
        System.out.println(line);
    }

    public static void main(String[] args) {
        hello();

        Scanner s = new Scanner(System.in);

        while (true) {
            System.out.print("Please, give me an instruction: ");
            String command = s.nextLine();
            
            if (command.equalsIgnoreCase("bye")) {
                // stop the commands when "bye" is inputted
                goodbye();
                break;
            } else if (command.equalsIgnoreCase("list")) {
                // stop the commands when "bye" is inputted
                list();
            } else {
                // else, add item to log
                add(command);
            }
        }

        s.close();
    }
}
