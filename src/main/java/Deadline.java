public class Deadline extends ToDo {
    private String Type = "D";
    private String due;

    public Deadline(boolean isDone, String Task, String due) {
        super(isDone, Task); 
        setDue(due);
    }

    public String getDue() {
        return due;
    }

    public void setDue(String dueDate) {
        this.due = dueDate;
    }

    @Override
    public String printTask() {
        String symbol = this.getDone() ? "X" : " ";
        return "["+ Type + "]" + "[" + symbol + "] " + getTask() + "(By: " + getDue() + ")";
    }
}