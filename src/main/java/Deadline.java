import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends ToDo {
    private String Type = "D";
    private LocalDate due;

    public Deadline(boolean isDone, String Task, LocalDate due) {
        super(isDone, Task); 
        setDue(due);
    }

    public LocalDate getDue() {
        return this.due;
    }

    public void setDue(LocalDate dueDate) {
        this.due = dueDate;
    }

    private String getDueString() {
        return this.getDue().format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String printTask() {
        String symbol = this.getDone() ? "X" : " ";
        return "["+ Type + "]" + "[" + symbol + "] " + getTask() + " (By: " + getDueString() + ")";
        
    }

    @Override
    public String toFileString() {
        String symbol = this.getDone() ? "1" : "0";
        return Type + " | " + symbol + " | " + getTask() + " | " + getDue();
    }
}