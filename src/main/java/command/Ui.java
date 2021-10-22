package command;


import module.Module;
import module.ModuleList;
import task.OverallTask;
import task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Ui {
    private static final String logo = "NoCap Logo";

    public static void printHelpMessage() {
        System.out.println("Display help messages: help");
        System.out.println("Add Module: add <module>");
        System.out.println("Delete Module: delete <Module>");
        System.out.println("List tasks: list task");
        System.out.println("List modules: list module");
        System.out.println("Show timetable: timetable");
        System.out.println("Add class to module: /m <module> addclass <description> /by <date>");
        System.out.println("Add task to module: /m <module> addtask <description> /by <date>");
        System.out.println("Add grade to module: /m <module> addgrade <gradeLetter>");
        System.out.println("Mark task as done: /m <module> done <taskIndex>");
        System.out.println("Delete all classes from a module: /m <module> deleteclass");
        System.out.println("Delete all tasks from a module: /m <module> deletetask");
        System.out.println("Delete grade from a module: /m <module> deletegrade");
        System.out.println("Exit programme: bye");
    }

    public static void missingDescription() {
        System.out.println("You are missing a description!");
    }

    public static void missingDate() {
        System.out.println("You are missing a date!");
    }

    public static void printStartMessage() {
        System.out.println("Welcome to NoCap");
    }

    public static void printEndMessage() {
        System.out.println("Thank you for using NoCap");
    }

    public static void invalidDate() {
        System.out.println("Missing /by");
    }

    public static void invalidWeightage() {
        System.out.println("Missing /w");
    }


    public static void wrongDateTimeFormat() {
        System.out.println("Wrong date format input!");
        System.out.println("Format: dd/MM/yyyy hhmm");
    }

    public static void inputNotInteger() {
        System.out.println("Input must be an integer!");
    }

    public static void switchSemesterMessage(String semester) {
        System.out.println("Semester successfully switched");
        System.out.println("You are now accessing semester: " + semester);
    }

    public static void addModuleNameMessage(ModuleList moduleList) {
        System.out.println("Module successfully added: ");
        moduleList.printModules();
    }

    public static void addModuleGradeMessage(Module module) {
        System.out.println("Module grade successfully added: ");
        System.out.println(module);
    }

    public static void addModuleCreditsMessage(Module module) {
        System.out.println("Module credits successfully added: ");
        System.out.println(module);
    }

    public static void addModuleClassMessage(Module module) {
        System.out.println("Module Class successfully added: ");
        System.out.println(module.getScheduleList());
    }

    public static void addTaskMessage(Task task, String moduleName) {
        System.out.println("Added new task to " + moduleName);
        System.out.println(task);
    }

    public static void printTaskList(String module, int taskCount) {
        System.out.println("Task List for " + module.toUpperCase(Locale.ROOT) + ":");
        System.out.println("There are " + taskCount + " tasks");
    }

    public static void printWeeklyTaskList(String module, int taskCount) {
        System.out.println("Task List for " + module.toUpperCase(Locale.ROOT) + ":");
        System.out.println("There are " + taskCount + " tasks due within 7 days");
    }

    public static void printMonthlyTaskList(String module, int taskCount) {
        System.out.println("Task List for " + module.toUpperCase(Locale.ROOT) + ":");
        System.out.println("There are " + taskCount + " tasks due within a month");
    }

    public static void printYearlyTaskList(String module, int taskCount) {
        System.out.println("Task List for " + module.toUpperCase(Locale.ROOT) + ":");
        System.out.println("There are " + taskCount + " tasks due within a year");
    }

    public static void printEmptyTaskList(String module) {
        System.out.println("There are no tasks due in " + module.toUpperCase(Locale.ROOT));
    }

    public static void printSortListByDate(String module) {
        System.out.println(module.toUpperCase(Locale.ROOT) + " successfully sorted by date");
    }

    public static void printSortListByStatus(String module) {
        System.out.println(module.toUpperCase(Locale.ROOT) + " successfully sorted by status");
    }

    public static void loadFileSuccessful() {
        System.out.println("Modules loaded successfully");
    }

    public static void printNoSaveFileMessage() {
        System.out.println("No save file found, starting with an empty template");
    }

    public static void printInvalidListFormat() {
        System.out.println("The list format is wrong! Please use list module/tasks "
                + "[ sortbydate | sortbystatus ] [ w | m | y]");
        System.out.println("Please refer to the user guide or help function for more details!");
    }

    public static void printInvalidIndex() {
        System.out.println("Task with the specified index not found!");
    }

    public static void printInvalidKeyword() {
        System.out.println("Task with the specified keyword not found!");
    }

    public static void printTaskFound() {
        System.out.println("The following task(s) are found:");
    }

    public static void printSelectIndex() {
        System.out.println("Please choose a task to perform the action:");
    }

    public static void printMarkDoneMessage(Task task) {
        System.out.println("Task is completed:" + System.lineSeparator() + task);
    }

    public static void printMarkNotDoneMessage(Task task) {
        System.out.println("Task is marked not complete:" + System.lineSeparator() + task);
    }

    public static void printExitMessage() {
        System.out.println("Thank you for using NoCap!");
        System.out.println("Have a nice day!");
    }

    public static void duplicateModuleError() {
        System.out.println("This module already exists!");
    }

    public static void printInvalidInputMessage() {
        System.out.println("Invalid Input!");
    }

    public static void printInvalidModuleNameMessage() {
        System.out.println("Invalid Module name!");
    }

    public static void printOverallListOrderedByDate(List<OverallTask> newTaskList) {
        System.out.println("Tasks sorted by date: ");
        if (newTaskList.isEmpty()) {
            System.out.println("You have no tasks");
            return;
        }
        for (int i = 0; i < newTaskList.size(); i++) {
            newTaskList.get(i).updateOverdue();
            System.out.println((i + 1) + ". " + newTaskList.get(i));
        }
    }

    public static void printOverallListOrderedByStatus(List<OverallTask> newTaskList) {
        System.out.println("Tasks sorted by status: ");
        if (newTaskList.isEmpty()) {
            System.out.println("You have no tasks");
            return;
        }
        for (int i = 0; i < newTaskList.size(); i++) {
            newTaskList.get(i).updateOverdue();
            System.out.println((i + 1) + ". " + newTaskList.get(i));
        }
    }

    public static void printOverallWeeklyTasks(List<OverallTask> newTaskList) {
        System.out.println("Weekly tasks: ");
        if (newTaskList.isEmpty()) {
            System.out.println("You have no weekly tasks");
            return;
        }
        for (int i = 0; i < newTaskList.size(); i++) {
            newTaskList.get(i).updateOverdue();
            System.out.println((i + 1) + ". " + newTaskList.get(i));
        }
    }

    public static void printOverallMonthlyTasks(List<OverallTask> newTaskList) {
        System.out.println("Monthly tasks: ");
        if (newTaskList.isEmpty()) {
            System.out.println("You have no monthly tasks");
            return;
        }
        for (int i = 0; i < newTaskList.size(); i++) {
            newTaskList.get(i).updateOverdue();
            System.out.println((i + 1) + ". " + newTaskList.get(i));
        }
    }

    public static void printOverallYearlyTasks(List<OverallTask> newTaskList) {
        System.out.println("Yearly tasks: ");
        if (newTaskList.isEmpty()) {
            System.out.println("You have no yearly tasks");
            return;
        }
        for (int i = 0; i < newTaskList.size(); i++) {
            newTaskList.get(i).updateOverdue();
            System.out.println((i + 1) + ". " + newTaskList.get(i));
        }
    }

    public static void printAllOverallTasks(ArrayList<OverallTask> overallTaskList) {
        System.out.println("All tasks: ");
        if (overallTaskList.isEmpty()) {
            System.out.println("You have no tasks");
            return;
        }
        for (int i = 0; i < overallTaskList.size(); i++) {
            overallTaskList.get(i).updateOverdue();
            System.out.println((i + 1) + ". " + overallTaskList.get(i));
        }
    }
}
