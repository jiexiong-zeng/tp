package task;

import command.DateParser;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Task {
    private static final Logger logger = Logger.getLogger(Task.class.getName());
    protected String description;
    protected boolean isDone;
    protected String date;
    protected LocalDateTime deadline;

    /**
     * temp message.
     *
     * @param description The description of the task given by the user
     */
    public Task(String description, String date) {
        logger.setLevel(Level.OFF);
        setDescription(description);
        setDate(date);
        setDone(false);
        setDeadline(date);
    }

    /**
     * For deserialization from JSON file.
     */
    public Task() {
    }

    //Getters
    public String getDescription() {
        return this.description;
    }

    public String getDate() {
        return this.date;
    }

    public boolean isDone() {
        return isDone;
    }

    //Setters
    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDeadline(String date) {
        logger.log(Level.INFO, "Successfully set Task deadline...");
        this.deadline = DateParser.parseDate(date);
    }

    public String getDeadline() {
        return DateParser.dateStringOutput(this.deadline);
    }

    public void markDone() {
        logger.log(Level.INFO, "Successfully marked Task as done...");
        this.isDone = true;
    }

    public String createStatusIcon() {
        return "[" + (this.isDone ? "X" : " ") + "] ";
    }

    public String toString() {
        return  createStatusIcon() + getDescription() + " by: "  + getDeadline();
        //the original line fails the tests?
        //return  createStatusIcon() + getDescription() + " by: " + "(" + getDeadline() + ")";
    }
}
