package task;

import command.parser.Parser;
import command.Ui;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaskList {
    protected static final String EMPTY_STRING = "";
    protected static final int DAYS_IN_A_WEEK = 7;
    protected static final int DAYS_IN_A_MONTH = 31;
    protected static final int DAYS_IN_A_YEAR = 366;
    protected static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    protected ArrayList<Task> taskList;
    protected int taskCount;

    public TaskList() {
        logger.setLevel(Level.OFF);
        this.taskList = new ArrayList<>();
    }

    //Getters
    public int getTaskCount() {
        logger.log(Level.INFO, "Get task count");
        return taskCount;
    }

    public ArrayList<Task> getTaskList() {
        logger.log(Level.INFO, "Get task list");
        return taskList;
    }

    public int size() {
        logger.log(Level.INFO, "Get size of task list");
        return this.taskList.size();
    }

    //Setters
    public void setTaskList(ArrayList<Task> taskList) {
        logger.log(Level.INFO, "Set task list");
        this.taskList = taskList;
    }

    public void setTaskCount(int taskCount) {
        logger.log(Level.INFO, "Set task count");
        this.taskCount = taskCount;
    }

    //assert index is valid
    public Task get(int index) {
        logger.log(Level.INFO, "Get task");
        return this.taskList.get(index);
    }

    public void delete(Task task) {
        logger.log(Level.INFO, "Successfully deleted task");
        taskList.remove(task);
        this.taskCount = taskList.size();
    }

    protected static String getDate(String description) {
        try {
            int datePos = description.indexOf(Parser.START_OF_DATE);
            return description.substring(datePos).replace(Parser.START_OF_DATE, EMPTY_STRING).trim();
        } catch (StringIndexOutOfBoundsException e) {
            return EMPTY_STRING;
        }
    }

    protected static String removeDate(String description) {
        try {
            int datePos = description.indexOf(Parser.START_OF_DATE);
            return description.substring(0, datePos).trim();
        } catch (StringIndexOutOfBoundsException e) {
            return EMPTY_STRING;
        }
    }

    /**
     * Create a new Task object and add it to the tasks list.
     *
     * @param userInput task description input by user
     */
    public void addTask(String module, String userInput) throws DateTimeException {
        String date = getDate(userInput);
        if (date.isBlank()) {
            Ui.missingDate();
        } else {
            try {
                String description = removeDate(userInput);
                Task newTask = new Task(description, date);
                this.taskList.add(taskCount, newTask);
                this.taskCount = taskList.size();
                Ui.addTaskMessage(newTask, module);
                logger.log(Level.INFO, "Successfully added task");
            } catch (DateTimeException e) {
                Ui.wrongDateTimeFormat();
            }
        }
    }

    protected static final Comparator<Task> sortByDate = Comparator.comparing(t -> t.deadline);

    protected static final Comparator<Task> sortByStatus = (t1, t2) -> {
        if (t1.isDone && !t2.isDone) {
            return -1;
        }
        if (!t1.isDone && t2.isDone) {
            return 1;
        }
        return 0;
    };

    protected boolean isWeekly(Task t) {
        LocalDate date = LocalDate.now();
        Period p = Period.between(date, t.deadline.toLocalDate()).normalized();
        int day = p.getYears() * DAYS_IN_A_YEAR + p.getMonths() * DAYS_IN_A_MONTH + p.getDays();
        return day <= DAYS_IN_A_WEEK;
    }

    protected boolean isMonthly(Task t) {
        LocalDate date = LocalDate.now();
        Period p = Period.between(date, t.deadline.toLocalDate()).normalized();
        int day = p.getYears() * DAYS_IN_A_YEAR + p.getMonths() * DAYS_IN_A_MONTH + p.getDays();
        return day <= DAYS_IN_A_MONTH;
    }

    protected boolean isYearly(Task t) {
        LocalDate date = LocalDate.now();
        Period p = Period.between(date, t.deadline.toLocalDate()).normalized();
        int day = p.getYears() * DAYS_IN_A_YEAR + p.getMonths() * DAYS_IN_A_MONTH + p.getDays();
        return day <= DAYS_IN_A_YEAR;
    }

    public void showAllWeekly(String module) {
        //  logger.log(Level.INFO, "Printing weekly tasks list...");
        ArrayList<Task> list = new ArrayList<>(weeklyTaskList());
        Ui.printWeeklyTaskList(module, list.size());
        printTasks(list);
    }

    public void showAllMonthly(String module) {
        logger.log(Level.INFO, "Printing monthly tasks list...");
        ArrayList<Task> list = new ArrayList<>(monthlyTaskList());
        Ui.printMonthlyTaskList(module, list.size());
        printTasks(list);
    }

    public void showAllYearly(String module) {
        logger.log(Level.INFO, "Printing yearly tasks list...");
        ArrayList<Task> list = new ArrayList<>(yearlyTaskList());
        Ui.printYearlyTaskList(module, list.size());
        printTasks(list);
    }

    public ArrayList<Task> weeklyTaskList() {
        ArrayList<Task> list = new ArrayList<>();
        for (Task task : taskList) {
            assert (task != null);
            if (isWeekly(task)) {
                list.add(task);
            }
        }
        return list;
    }

    public ArrayList<Task> monthlyTaskList() {
        ArrayList<Task> list = new ArrayList<>();
        for (Task task : taskList) {
            assert (task != null);
            if (isMonthly(task)) {
                list.add(task);
            }
        }
        return list;
    }

    public ArrayList<Task> yearlyTaskList() {
        ArrayList<Task> list = new ArrayList<>();
        for (Task task : taskList) {
            assert (task != null);
            if (isYearly(task)) {
                list.add(task);
            }
        }
        return list;
    }

    public void printTasks(ArrayList<Task> taskList) {
        int index = 1;
        for (Task task : taskList) {
            if (task != null) {
                task.updateOverdue();
                System.out.print(index + ".");
                System.out.println(task);
                index++;
            }
        }
    }

    public void sortTaskListByDate(String module) {
        logger.log(Level.INFO, "Sorting tasks list by date...");
        taskList.sort(sortByDate);
        Ui.printSortListByDate(module);
    }

    public void sortTaskListByStatus(String module) {
        logger.log(Level.INFO, "Sorting tasks list by status...");
        taskList.sort(sortByStatus);
        Ui.printSortListByStatus(module);
    }

    @Override
    public String toString() {
        return taskList.toString();
    }
}


