package task;

import command.Ui;
import module.Module;
import module.ModuleList;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class OverallTaskList extends TaskList {
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    protected ArrayList<OverallTask> overallTaskList;

    public OverallTaskList(ModuleList moduleList) {
        this.overallTaskList = new ArrayList<>();
        addAllModuleListTasks(moduleList);
    }

    public ArrayList<OverallTask> getOverallTaskList() {
        return overallTaskList;
    }

    public void addAllModuleListTasks(ModuleList moduleList) {
        for (Module module : moduleList.getModuleList()) {
            String moduleName = module.getModuleName();
            for (Task task : module.getTaskList().taskList) {
                overallTaskList.add(new OverallTask(task, moduleName));
            }
            for (GradableTask gradableTask : module.getGradableTaskList().gradableTaskList) {
                overallTaskList.add(new OverallTask(gradableTask, moduleName));
            }
        }
        assert (!overallTaskList.isEmpty() || moduleList.getModuleList().isEmpty());
        logger.log(Level.INFO, "Add all tasks from module list to overall task list");
    }

    public void sortByDateAndPrint() {
        List<OverallTask> newTaskList = overallTaskList
                .stream()
                .sorted(OverallTask.dateComparator)
                .collect(Collectors.toList());
        Ui.printOverallListOrderedByDate(newTaskList);
        logger.log(Level.INFO, "Sort overall task by date");
    }

    public void sortByStatusAndPrint() {
        List<OverallTask> newTaskList = overallTaskList
                .stream()
                .sorted(OverallTask.statusComparator.reversed())
                .collect(Collectors.toList());
        Ui.printOverallListOrderedByStatus(newTaskList);
        logger.log(Level.INFO, "Sort overall task by status");
    }

    public void printWeeklyTasks() {
        List<OverallTask> newTaskList = overallTaskList
                .stream()
                .filter(this::isWeekly)
                .collect(Collectors.toList());
        Ui.printOverallWeeklyTasks(newTaskList);
        logger.log(Level.INFO, "print overall weekly tasks");
    }

    public void printMonthlyTasks() {
        List<OverallTask> newTaskList = overallTaskList
                .stream()
                .filter(this::isMonthly)
                .collect(Collectors.toList());
        Ui.printOverallMonthlyTasks(newTaskList);
        logger.log(Level.INFO, "print overall monthly tasks");
    }

    public void printYearlyTasks() {
        List<OverallTask> newTaskList = overallTaskList
                .stream()
                .filter(this::isYearly)
                .collect(Collectors.toList());
        Ui.printOverallYearlyTasks(newTaskList);
        logger.log(Level.INFO, "print overall yearly tasks");
    }

    public void printAllTasks() {
        Ui.printAllOverallTasks(overallTaskList);
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for (OverallTask task : overallTaskList) {
            out.append(task.toString()).append(System.lineSeparator());
        }
        return out.toString();
    }


}
