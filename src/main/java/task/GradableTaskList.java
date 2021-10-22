package task;

import command.parser.Parser;
import command.Ui;

import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GradableTaskList extends TaskList {
    protected ArrayList<GradableTask> gradableTaskList;
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public GradableTaskList() {
        this.gradableTaskList = new ArrayList<>();
    }

    public ArrayList<GradableTask> getGradableTaskList() {
        return gradableTaskList;
    }

    protected static String getDate(String description) {
        try {
            int datePos = description.indexOf(Parser.START_OF_DATE);
            int weightagePos = description.indexOf(Parser.START_OF_WEIGHTAGE);
            return description.substring(datePos, weightagePos).replace(Parser.START_OF_DATE, "").trim();
        } catch (StringIndexOutOfBoundsException e) {
            return "";
        }
    }

    private static int getWeightage(String description) {
        try {
            int weightagePos = description.indexOf(Parser.START_OF_WEIGHTAGE);
            return Integer.parseInt(description.substring(weightagePos).replace(Parser.START_OF_WEIGHTAGE, "").trim());
        } catch (StringIndexOutOfBoundsException e) {
            return 0;
        }
    }

    public void addGradableTask(String module, String userInput) throws DateTimeException {
        logger.log(Level.INFO, "Successfully added task");
        String date = getDate(userInput);
        int weightage = getWeightage(userInput);
        if (date.isBlank()) {
            Ui.missingDate();
        }
        if (weightage == 0) {
            Ui.missingDate();
        } else {
            try {
                String description = removeDate(userInput);
                GradableTask newGradableTask = new GradableTask(description, date, weightage);
                this.gradableTaskList.add(taskCount, newGradableTask);
                this.taskCount = gradableTaskList.size();
                Ui.addTaskMessage(newGradableTask, module);
            } catch (DateTimeException e) {
                Ui.wrongDateTimeFormat();
            }
        }
    }

    public GradableTask getGradableTask(int index) {
        logger.log(Level.INFO, "Get GradableTask");
        return this.gradableTaskList.get(index);
    }

    @Override
    public String toString() {
        int index = 1;
        String gradableTaskPrint = "";
        for (GradableTask g : gradableTaskList) {
            if (g != null) {
                gradableTaskPrint = gradableTaskPrint + String.valueOf(index) + ".\n";
                gradableTaskPrint = gradableTaskPrint + g.toString() + "\n";
                index++;
            }
        }
        return gradableTaskPrint;
    }

}
