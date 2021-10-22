package schedule;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScheduleList {
    protected ArrayList<Schedule> scheduleList;
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public ScheduleList() {
        this.scheduleList = new ArrayList<Schedule>();
    }

    public void addClass(Schedule schedule) {
        this.scheduleList.add(schedule);
    }

    public void addClass(String input) {
        String[] scheduleInfo = input.split("/");
        assert scheduleInfo.length == 4;
        Schedule schedule = new Schedule(scheduleInfo[0], scheduleInfo[1], scheduleInfo[2], scheduleInfo[3]);
        this.scheduleList.add(schedule);
        logger.log(Level.INFO, "Schedule added successfully");
    }

    public void deleteClass() {
        while (this.scheduleList.size() != 0) {
            this.scheduleList.remove(scheduleList.get(0));
        }
    }

    public int size() {
        return this.scheduleList.size();
    }

    public Schedule getSchedule(int index) {
        return this.scheduleList.get(index);
    }

    public ArrayList<Schedule> getScheduleList() {
        return this.scheduleList;
    }

    @Override
    public String toString() {
        int index = 1;
        String schedulePrint = "";
        for (Schedule s : scheduleList) {
            if (s != null) {
                schedulePrint = schedulePrint + String.valueOf(index) + ".\n";
                schedulePrint = schedulePrint + s.toString() + "\n";
                index++;
            }
        }
        return schedulePrint;
    }
}
