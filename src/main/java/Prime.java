import java.util.Scanner;

public class Prime {
    private static final Log log = new Log();

    public static void main(String[] args) {
        Ui.showHello();

        Scanner s = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            try {
                Ui.showMessage("Please, give me an instruction: ");
                String input = s.nextLine().trim();
                Ui.showLine();

                isRunning = PrimeParser.parse(input, log);

                Ui.showLine();

            } catch (PrimeException e) {
                Ui.showError(e.getMessage());
                Ui.showLine();
            }
        }

        s.close();

        // while (true) {
        //     System.out.print("Please, give me an instruction: ");
        //     String input = s.nextLine().trim();
        //     System.out.println(line);

        //     if (input.isEmpty()) {
        //         System.out.println("Hmm.. you didn't say anything...");
        //         System.out.println(line);
        //     }

        //     String[] parts = input.split("\\s+", 2);
        //     String command = parts[0].toLowerCase();
        //     String rest = parts.length > 1 ? parts[1] : "";
            
        //     if (command.equalsIgnoreCase("bye")) {
        //         // stop the commands when "bye" is inputted
        //         goodbye();
        //         System.out.println(line);
        //         break;
        //     } else if (command.equalsIgnoreCase("list")) {
        //         // list the current items
        //         logSize();
        //         list();
        //         System.out.println(line);
        //     } else if (command.equalsIgnoreCase("mark")) {
        //         // input should be "mark [int]"
        //         if (parts.length < 2) {
        //             System.out.println("Please tell me which task to mark.");
        //             System.out.println(line);
        //             continue;
        //         }
        //         int index = Integer.parseInt(parts[1]);
        //         mark(index);
        //     } else if (command.equalsIgnoreCase("unmark")) {
        //         // input should be "unmark [int]"
        //         if (parts.length < 2) {
        //             System.out.println("Please tell me which task to unmark.");
        //             System.out.println(line);
        //             continue;
        //         }
        //         int index = Integer.parseInt(parts[1]);
        //         unmark(index);
        //     } else if (command.equalsIgnoreCase("todo")) {
        //         // input is a todo
        //         // todos need task descriptions
        //         if (rest.isEmpty()) {
        //             System.out.println("Please give me a task description.");
        //             System.out.println(line);
        //             continue;
        //         } 
        //         addTodo(rest);
        //         logSize();
        //         System.out.println(line);
                
        //     } else if (command.equalsIgnoreCase("deadline")) {
        //         // input is a deadline
        //         // deadlines need task descriptions and a deadline 
        //         if (rest.isEmpty()) {
        //             System.out.println("I am sorry, but this instruction is incomplete.");
        //             System.out.println(line);
        //             continue;
        //         }
        //         String[] dueStrings = rest.split("/by ", 2);
        //         if (dueStrings.length != 2) {
        //             System.out.println("Please provide me with the task description and the due date.");
        //             System.out.println(line);
        //             continue;
        //         }
        //         addDeadline(dueStrings[0], dueStrings[1]);
        //         logSize();
        //         System.out.println(line);
        //     } 
        //     else if (command.equalsIgnoreCase("event")) {
        //         // input is a event
        //         // events need task desc, start and end time
        //         if (rest.isEmpty()) {
        //             System.out.println("I am sorry, but this instruction is incomplete");
        //             System.out.println(line);
        //             continue;
        //         }
        //         String[] timingsArray = rest.split("/from ", 2);
        //         if (timingsArray.length != 2) {
        //             System.out.println("Please provide me with the task description, start and end times.");
        //             System.out.println(line);
        //             continue;
        //         }
        //         String timings = timingsArray[1];
        //         String[] dates = timings.split("/to ", 2);
        //         if (dates.length != 2) {
        //             System.out.println("Are you missing the start or end times? They must be in the correct order.");
        //             System.out.println(line);
        //             continue;
        //         }
        //         addEvent(timingsArray[0].trim(), dates[0], dates[1]);
        //         logSize();
        //         System.out.println(line);
        //     } else {
        //         // else, say that there is an error
        //         System.out.println("I am sorry, I do not recognise this instruction...");
        //         System.out.println(line);
        //         continue;
        //     }
        // }

        // s.close();
    }
}
