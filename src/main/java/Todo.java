public class ToDo {
    private String Type = "T";
    private boolean isDone = false;
    private String Task;
    
    public ToDo(boolean isDone, String Task) {
        setDone(isDone);
        setTask(Task);
    }

    // getter for completion
    public boolean getDone() {
        return this.isDone;
    }

    // setter for completion
    public void setDone(boolean isCompleted) {
        this.isDone = isCompleted;
    }

    // getter for task
    public String getTask() {
        return this.Task;
    }

    // setter for task
    public void setTask(String input) {
        this.Task = input;
    }

    public String printTask() {
        String symbol = this.getDone() ? "X" : " ";
        return "["+ Type + "]" + "[" + symbol + "] " + getTask();
    }

}
