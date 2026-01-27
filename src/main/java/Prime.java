import java.io.Console;
import java.util.Scanner;

public class Prime {
    public static void main(String[] args) {
        String line = "________________________________________________";
        String greet = "I AM OPTIMUS PRIME.\nWhat can I do for you?";
        String bye = "Goodbye.";

        // prints Prime's greeting 
        System.out.println(line);
        System.out.println(greet);
        System.out.println(line);

        Scanner s = new Scanner(System.in);

        while (true) {
            System.out.print("Please, give me an instruction: ");
            String command = s.nextLine();
            
            if (command.equalsIgnoreCase("bye")) {
                System.out.println(line);
                System.out.println(bye);
                System.out.println(line);
                break;
            } else {
                System.out.println(line);
                System.out.println("You said: " + command);
                System.out.println(line);
            }
        }

        s.close();

        
        

        
        
    }
}
