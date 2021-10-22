package schedule;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Objects;

public class Schedule {
    protected String startTime;
    protected String location;
    protected String day;
    protected String comment;

    public Schedule(String day, String startTime, String location, String comment) {
        assert location.length() <= 16;
        assert comment.length() <= 16;

        this.day = day;
        this.startTime = startTime;
        this.location = location;
        this.comment = comment;
    }

    private boolean isCorrectDayFormat(String day) {
        return Objects.equals(day, "MON") || Objects.equals(day, "TUE") || Objects.equals(day, "WED")
                || Objects.equals(day, "THU") || Objects.equals(day, "FRI") || Objects.equals(day, "SAT");
    }

    /**
     * For deserialization from JSON file.
     */
    public Schedule() {
    }

    //Getters
    public String getLocation() {
        return this.location;
    }

    public String getComment() {
        return this.comment;
    }

    public String getDay() {
        return day;
    }

    public String getStartTime() {
        return startTime;
    }

    //Setters
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String toString() {
        return "Day: " + day
                + "\nStart Time: " + startTime
                + "\nLocation: " + location
                + "\nComments: " + comment;
    }
}
