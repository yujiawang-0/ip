public class Ui {
    public static final String LINE = "_______________________________________________________________________________________";

    // prints greeting when called
    public static void showHello() {
        System.out.println(LINE);
        System.out.println("I AM OPTIMUS PRIME.\nHello, little one.");
        System.out.println(LINE);
    }

    // prints hello when called
    public static void showHelloTwo() {
        System.out.println("Hello, child.");
    }
    
    // prints goodbye when called
    public static void showGoodbye() {
        System.out.println("Goodbye, little one.");
    }

    // prints line
    public static void showLine() {
        System.out.println(LINE);
    }

    // prints message
    public static void showMessage(String message) {
        System.out.println(message);
    }

    // prints error
    public static void showError(String error) {
        System.out.println(error);
    }

}
