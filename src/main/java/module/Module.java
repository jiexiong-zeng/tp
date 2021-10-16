package module;


import command.storage.StorageDecoder;
import task.Task;
import task.TaskList;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Module {
    protected String letterGrade;
    protected String moduleName;
    protected Schedule schedule;
    public TaskList taskList;
    private ArrayList<Schedule> scheduleList;
    protected int credits;
    private static final Logger logger = Logger.getLogger(StorageDecoder.class.getName());

    public Module(String moduleName) {
        logger.setLevel(Level.OFF);
        assert !moduleName.equals("");
        this.moduleName = moduleName;
        this.letterGrade = null;
        this.scheduleList = new ArrayList<Schedule>();
        this.credits = 0;
        this.taskList = new TaskList();
    }

    /**
     * For deserialization from JSON file.
     */
    public Module() {
    }

    //Getters and Setters
    public String getLetterGrade() {
        return letterGrade;
    }

    public void setLetterGrade(String letterGrade) {
        this.letterGrade = letterGrade;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    public ArrayList<Schedule> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(ArrayList<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    //Methods
    public int size() {
        return this.scheduleList.size();
    }

    public Schedule get(int index) {
        assert index >= 0;
        return this.scheduleList.get(index);
    }

    public void addGrade(String letterGrade) {
        this.letterGrade = letterGrade;
    }

    public void deleteGrade() {
        this.letterGrade = null;
    }

    public void addClass(Schedule schedule) {
        this.scheduleList.add(schedule);
    }

    //overloading to take in String input * Added by jiexiong to keep Parser clean
    public void addClass(String input) {
        String[] scheduleInfo = input.split("/");
        assert scheduleInfo.length == 4;
        Schedule schedule = new Schedule(scheduleInfo[0], scheduleInfo[1], scheduleInfo[2], scheduleInfo[3]);
        this.scheduleList.add(schedule);
        logger.log(Level.INFO,"Schedule added successfully");
    }

    public void deleteClass() {
        while (this.scheduleList.size() != 0) {
            this.scheduleList.remove(scheduleList.get(0));
        }
    }

    public void addTask(String userInput) {
        this.taskList.addTask(this.moduleName, userInput);
    }

    public void deleteTask(Task task) {
        this.taskList.delete(task);
    }

    public void addCredits(int credits) {
        this.credits = credits;
    }

    public void showInformation() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        int index  = 1;
        String schedulePrint = new String();
        for (Schedule s : scheduleList) {
            if (s != null) {
                schedulePrint = schedulePrint + String.valueOf(index) + ".\n";
                schedulePrint = schedulePrint + s.toString() + "\n";
                index++;
            }
        }
        return "Module name: " + moduleName
                + "\nCREDITS: " + credits
                + "\n--------------------------- "
                + "\nSCHEDULE: \n" + schedulePrint
                + "--------------------------- "
                + "\nGRADE: " + letterGrade
                + "\nTASKS: " + taskList;
    }
}
