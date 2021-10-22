package module;


import command.NoCap;
import schedule.Schedule;
import schedule.ScheduleList;
import task.GradableTaskList;
import task.Task;
import task.TaskList;

import java.util.logging.Level;
import java.util.logging.Logger;


public class Module {
    protected String letterGrade;
    protected double points;
    protected String moduleName;
    protected Schedule schedule;
    public TaskList taskList;
    private ScheduleList scheduleList;
    protected int credits;
    protected GradableTaskList gradableTaskList;
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public Module(String moduleName) {
        logger.setLevel(Level.OFF);
        assert !moduleName.equals("");
        this.moduleName = moduleName;
        this.letterGrade = null;
        this.scheduleList = new ScheduleList();
        this.credits = 0;
        this.taskList = new TaskList();
        this.gradableTaskList = new GradableTaskList();
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

    //    public Schedule getSchedule() {
    //        return schedule;
    //    }
    //
    //    public void setSchedule(Schedule schedule) {
    //        this.schedule = schedule;
    //    }

    public TaskList getTaskList() {
        return taskList;
    }

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    public ScheduleList getScheduleList() {
        return this.scheduleList;
    }

    public void setScheduleList(ScheduleList scheduleList) {
        this.scheduleList = scheduleList;
    }

    public GradableTaskList getGradableTaskList() {
        return this.gradableTaskList;
    }

    public void addGradableTask(String userInput) {
        this.gradableTaskList.addGradableTask(this.moduleName, userInput);
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
        return this.scheduleList.getSchedule(index);
    }

    public void addGrade(String letterGrade) {
        this.letterGrade = letterGrade;
        updatePoints();
    }

    public void deleteGrade() {
        this.letterGrade = null;
    }

    private void updatePoints() {
        switch (letterGrade) {
        case "A+":
        case "A":
            points = 5;
            break;
        case "A-":
            points = 4.5;
            break;
        case "B+":
            points = 4.0;
            break;
        case "B":
            points = 3.5;
            break;
        case "B-":
            points = 3.0;
            break;
        case "C+":
            points = 2.5;
            break;
        case "C":
            points = 2.0;
            break;
        case "D+":
            points = 1.5;
            break;
        case "D":
            points = 1.0;
            break;
        case "F":
            points = 0;
            break;
        default:
            break;
        }
    }

    public double getPoints() {
        return points;
    }

    public void addClass(Schedule schedule) {
        this.scheduleList.addClass(schedule);
    }

    //overloading to take in String input * Added by jiexiong to keep Parser clean
    public void addClass(String input) {
        this.scheduleList.addClass(input);
    }

    public void deleteClass() {
        this.scheduleList.deleteClass();
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

        return "Module name: " + moduleName
                + "\nCREDITS: " + credits
                + "\n--------------------------- "
                + "\nSCHEDULE: \n" + scheduleList
                + "--------------------------- "
                + "\nGRADE: " + letterGrade
                + "\nTASKS: " + taskList
                + "\nBREAKDOWN: \n" + gradableTaskList;
    }
}
