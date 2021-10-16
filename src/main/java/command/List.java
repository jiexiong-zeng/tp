package command;

import module.Module;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class List {
    private static final Logger logger = Logger.getLogger(List.class.getName());

    public List() {
        logger.setLevel(Level.OFF);
    }

    boolean isEmpty(Module module) {
        if (module.taskList.size() == 0) {
            Ui.printEmptyTaskList(module.getModuleName());
            return true;
        }
        return false;
    }

    void sortByDate(ArrayList<Module> moduleList) {
        for (Module module : moduleList) {
            if (!isEmpty(module)) {
                module.taskList.sortTaskListByDate(module.getModuleName());
            }
        }
    }

    void sortByStatus(ArrayList<Module> moduleList) {
        for (Module module : moduleList) {
            if (!isEmpty(module)) {
                module.taskList.sortTaskListByStatus(module.getModuleName());
            }
        }
    }

    void listWeekly(ArrayList<Module> moduleList) {
        for (Module module : moduleList) {
            if (!isEmpty(module)) {
                module.taskList.showAllWeekly(module.getModuleName());
            }
        }
    }

    void listAll(ArrayList<Module> moduleList) {
        for (Module module : moduleList) {
            if (!isEmpty(module)) {
                Ui.printTaskList(module.getModuleName(), module.taskList.getTaskCount());
                module.taskList.printTasks(module.taskList.getTaskList());
            }
        }
    }

    void listMonthly(ArrayList<Module> moduleList) {
        for (Module module : moduleList) {
            if (!isEmpty(module)) {
                module.taskList.showAllMonthly(module.getModuleName());
            }
        }
    }

    void listYearly(ArrayList<Module> moduleList) {
        for (Module module : moduleList) {
            if (!isEmpty(module)) {
                module.taskList.showAllYearly(module.getModuleName());
            }
        }
    }

    void listParser(String input) {
        Parser.splitInput(input);
        if (Parser.taskType.equals(Parser.MODULE)) {
            NoCap.moduleList.printModules();
        } else if (Parser.taskType.equals(Parser.TASK)) {
            ArrayList<Module> moduleList = new ArrayList<Module>(NoCap.moduleList.getModuleList());
            switch (Parser.taskDescription) {
            case Parser.SORT_BY_DATE:
                logger.log(Level.INFO, "Sort TaskList by date");
                sortByDate(moduleList);
                break;
            case Parser.SORT_BY_STATUS:
                logger.log(Level.INFO, "Sort TaskList by status");
                sortByStatus(moduleList);
                break;
            case Parser.SHOW_WEEK:
                logger.log(Level.INFO, "Print weekly TaskList");
                listWeekly(moduleList);
                break;
            case Parser.SHOW_MONTH:
                logger.log(Level.INFO, "Print monthly TaskList");
                listMonthly(moduleList);
                break;
            case Parser.SHOW_YEAR:
                logger.log(Level.INFO, "Print yearly TaskList");
                listYearly(moduleList);
                break;
            default:
                System.out.println("Showing all tasks");
                listAll(moduleList);
                break;
            }
        } else {
            Ui.printInvalidListFormat();
        }
    }
}