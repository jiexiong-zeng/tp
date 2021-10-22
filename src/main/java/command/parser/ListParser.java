package command.parser;

import command.NoCap;
import command.Ui;
import module.Module;
import task.OverallTaskList;
import task.Task;
import task.TaskList;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListParser {
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public ListParser() {
    }

    boolean isEmpty(Module module) {
        if (module.taskList.size() == 0) {
            Ui.printEmptyTaskList(module.getModuleName());
            return true;
        }
        return false;
    }

    private void sortByDate(Module module) {
        TaskList list = module.getTaskList();
        if (!isEmpty(module)) {
            list.sortTaskListByDate(module.getModuleName());
        }
    }

    private void sortByStatus(Module module) {
        TaskList list = module.getTaskList();
        if (!isEmpty(module)) {
            list.sortTaskListByStatus(module.getModuleName());
        }
    }

    private void listWeekly(Module module) {
        TaskList list = module.getTaskList();
        if (!isEmpty(module)) {
            list.showAllWeekly(module.getModuleName());
        }
    }

    private void listAll(Module module) {
        TaskList list = module.getTaskList();
        if (!isEmpty(module)) {
            Ui.printTaskList(module.getModuleName(), list.getTaskCount());
            list.printTasks(list.getTaskList());
        }
    }

    private void listMonthly(Module module) {
        TaskList list = module.getTaskList();
        if (!isEmpty(module)) {
            list.showAllMonthly(module.getModuleName());
        }
    }

    private void listYearly(Module module) {
        TaskList list = module.getTaskList();
        if (!isEmpty(module)) {
            list.showAllYearly(module.getModuleName());
        }
    }

    public void overallListParser(String input) {
        Parser.splitInput(input);
        if (Parser.taskType.equals(Parser.MODULE)) {
            NoCap.moduleList.printModules();
        } else if (Parser.taskType.equals(Parser.SEMESTERS)) {
            NoCap.semesterList.printSemesters();
        } else if (Parser.taskType.equals(Parser.TASK)) {
            OverallTaskList allTaskList = new OverallTaskList(NoCap.moduleList);
            switch (Parser.taskDescription) {
            case Parser.SORT_BY_DATE:
                logger.log(Level.INFO, "Sort TaskList by date");
                allTaskList.sortByDateAndPrint();
                //sortByDate(moduleList);
                break;
            case Parser.SORT_BY_STATUS:
                logger.log(Level.INFO, "Sort TaskList by status");
                allTaskList.sortByStatusAndPrint();
                //sortByStatus(moduleList);
                break;
            case Parser.SHOW_WEEK:
                logger.log(Level.INFO, "Print weekly TaskList");
                allTaskList.printWeeklyTasks();
                //listWeekly(moduleList);
                break;
            case Parser.SHOW_MONTH:
                logger.log(Level.INFO, "Print monthly TaskList");
                allTaskList.printMonthlyTasks();
                //listMonthly(moduleList);
                break;
            case Parser.SHOW_YEAR:
                allTaskList.printYearlyTasks();
                logger.log(Level.INFO, "Print yearly TaskList");
                //listYearly(moduleList);
                break;
            default:
                allTaskList.printAllTasks();
                //System.out.println("Showing all tasks");
                //listAll(moduleList);
                break;
            }
        } else {
            Ui.printInvalidListFormat();
        }
    }

    public void moduleListParser(Module module, String input) {
        switch (input) {
        case Parser.SORT_BY_DATE:
            sortByDate(module);
            break;
        case Parser.SORT_BY_STATUS:
            sortByStatus(module);
            break;
        case Parser.SHOW_WEEK:
            listWeekly(module);
            break;
        case Parser.SHOW_MONTH:
            listMonthly(module);
            break;
        case Parser.SHOW_YEAR:
            listYearly(module);
            break;
        default:
            listAll(module);
            break;
        }
    }
}