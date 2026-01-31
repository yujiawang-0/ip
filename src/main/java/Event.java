public class Event extends ToDo {
    private String Type = "E";
    private String startTime;
    private String endTime;

    public Event(boolean isDone, String Task, String startTime, String endTime) {
        super(isDone, Task); 
        setStart(startTime);
        setEnd(endTime);
    }

    public String getStart() {
        return this.startTime;
    }

    public String getEnd() {
        return this.endTime;
    }

    public void setStart(String start) {
        this.startTime = start;
    }

    public void setEnd(String end) {
        this.endTime = end;
    }

    @Override
    public String printTask() {
        String symbol = this.getDone() ? "X" : " ";
        return "["+ Type + "]" + "[" + symbol + "] " + getTask() + " (From: " + getStart() + "To: "+ getEnd() + ")";
    }

    @Override
    public String toFileString() {
        String symbol = this.getDone() ? "1" : "0";
        return Type + " | " + symbol + " | " + getTask() + " | " + getStart() +  " | " + getEnd() ;
    }
}

