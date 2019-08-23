public class Task {
    protected String description;
    protected boolean isDone;
    protected static int numTasks = 0;

    public Task(String description){
        if(description.length() == 0){
            throw new RuntimeException("☹ OOPS!!! The description of a task cannot be empty."); // what is string of whitespace
        }
        this.description = description;
        this.isDone = false;
        numTasks++;
    }

    public static int getNumTasks() { return numTasks; }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getDone(){
        return isDone;
    }

    public void setDone(boolean isDone){
        this.isDone = isDone;
    }

    @Override
    public String toString(){
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}