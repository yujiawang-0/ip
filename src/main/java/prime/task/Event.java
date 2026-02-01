package prime.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends ToDo {
    private String Type = "E";
    private LocalDate startTime;
    private LocalDate endTime;

    public Event(boolean isDone, String Task, LocalDate startTime, LocalDate endTime) {
        super(isDone, Task); 
        setStart(startTime);
        setEnd(endTime);
    }

    public LocalDate getStart() {
        return this.startTime;
    }

    public LocalDate getEnd() {
        return this.endTime;
    }

    public void setStart(LocalDate start) {
        this.startTime = start;
    }

    public void setEnd(LocalDate end) {
        this.endTime = end;
    }

    private String getStartString() {
        return this.getStart().format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    private String getEndString() {
        return this.getEnd().format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String printTask() {
        String symbol = this.getDone() ? "X" : " ";
        return "["+ Type + "]" + "[" + symbol + "] " + getTask() + " (From: " 
                + getStartString() + " To: "+ getEndString() + ")";
    }

    @Override
    public String toFileString() {
        String symbol = this.getDone() ? "1" : "0";
        return Type + " | " + symbol + " | " + getTask() + " | " 
                + getStart() +  " | " + getEnd() ;
    }
}

