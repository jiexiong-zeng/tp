package task;

import java.util.Comparator;

public class OverallTask extends Task {
    private final String moduleName;
    private final boolean gradable;
    private int weightage;

    public OverallTask(Task task, String moduleName) {
        super(task.description, task.date);
        this.isDone = task.isDone;
        this.moduleName = moduleName;
        this.gradable = false;
    }

    public OverallTask(GradableTask task, String moduleName) {
        super(task.description, task.date);
        this.isDone = task.isDone;
        this.moduleName = moduleName;
        this.gradable = true;
        this.weightage = task.weightage;
    }

    public static Comparator<OverallTask> dateComparator = Comparator.comparing(t -> t.deadline);

    public static Comparator<OverallTask> statusComparator = (task1, task2) -> {
        if (task1.isDone && !task2.isDone) {
            return -1;
        } else if (!task1.isDone && task2.isDone) {
            return 1;
        } else {
            return 0;
        }
    };

    private String getGradableString() {
        return gradable ? "[G]" : "[ ]";
    }

    private String getWeightageString() {
        return gradable ? "[Weightage: " + this.weightage + "%]" : "";
    }

    @Override
    public String toString() {
        return "[" + moduleName + "]" + getGradableString() + super.toString() + " " + getWeightageString();
    }

}
