package command.parser;

import command.NoCap;
import command.Ui;
import command.storage.StorageEncoder;
import module.Module;
import semester.Semester;
import task.Task;

import java.util.Locale;
import java.util.logging.Logger;

public class Parser {

    public static final String EMPTY_STRING = "";
    private static final String SPACE_STRING = " ";
    public static final String SWITCHSEMESTER = "switch";
    public static final String SEMESTERS = "semesters";
    public static final String CAP = "cap";
    public static final String ALLCAP = "allcap";
    public static final String TASK = "task";
    public static final String MODULE = "module";
    public static final String HELP = "help";
    public static final String ADD = "add";
    public static final String DELETE = "delete";
    public static final String LIST = "list";
    public static final String TIMETABLE = "timetable";
    public static final String EXIT = "bye";
    public static final String MODULETYPE = "/m";
    public static final String ADDCLASS = "addclass";
    public static final String ADDTASK = "addtask";
    public static final String ADDGRADABLE = "addgradable";
    public static final String ADDGRADE = "addgrade";
    public static final String ADDCREDIT = "addcredit";
    public static final String DELETECLASS = "deleteclass";
    public static final String DELETETASK = "deletetask";
    public static final String DELETEGRADE = "deletegrade";
    public static final String EDITDESCRIPTION = "editdesc";
    public static final String EDITDEADLINE = "editdate";
    public static final String NOTDONE = "notdone";
    public static final String DONE = "done";
    public static final String INFO = "info";
    public static final String START_OF_DATE = "/by";
    public static final String START_OF_WEIGHTAGE = "/w";
    public static final String SORT_BY_DATE = "sortbydate";
    public static final String SORT_BY_STATUS = "sortbystatus";
    public static final String SHOW_WEEK = "w";
    public static final String SHOW_MONTH = "m";
    public static final String SHOW_YEAR = "y";

    public static String taskType;
    public static String taskDescription;
    private Module module;
    private ListParser list = new ListParser();
    private ParserSearch parserSearch = new ParserSearch();
    protected boolean isExit;
    private static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public Parser() {
        this.isExit = false;
    }

    /**
     * Method to process user command.
     *
     * @param line User input
     */
    public void chooseTask(String line) {
        splitInput(line);
        switch (taskType) {
        case SWITCHSEMESTER:
            if (isEmptyDescription(taskDescription) || isNotInteger(taskDescription)) {
                break;
            }
            //move to SemesterList
            int semesterIndex = Integer.parseInt(taskDescription) - 1;
            NoCap.semesterList.setAccessedSemesterIndex(semesterIndex);
            Ui.switchSemesterMessage(NoCap.semesterList.get(semesterIndex).getSemester());
            break;
        case CAP:
            //move to SemesterList
            int index = NoCap.semesterList.getAccessedSemesterIndex();
            System.out.println(index);
            System.out.println("This semester's CAP: " + NoCap.semester.getCap());
            System.out.println("Cumulative CAP: " + NoCap.semesterList.getCap());
            break;
        case ALLCAP:
            //move to SemesterList
            for (Semester semester : NoCap.semesterList.getSemesterList()) {
                System.out.println(semester.getSemester() + " CAP: " + semester.getCap());
            }
            System.out.println("Cumulative CAP: " + NoCap.semesterList.getCap());
            break;
        case HELP:
            Ui.printHelpMessage();
            break;
        case ADD:
            if (isEmptyDescription(taskDescription) | isDuplicateModule(taskDescription)) {
                break;
            }
            NoCap.moduleList.add(taskDescription.toUpperCase(Locale.ROOT));
            Ui.addModuleNameMessage(NoCap.moduleList);
            StorageEncoder.encodeAndSaveSemesterListToJson(NoCap.semesterList);
            break;
        case DELETE:
            if (isEmptyDescription(taskDescription)) {
                break;
            }
            NoCap.moduleList.delete(taskDescription);
            StorageEncoder.encodeAndSaveSemesterListToJson(NoCap.semesterList);
            break;
        case LIST:
            list.overallListParser(taskDescription);
            break;
        case TIMETABLE:
            NoCap.moduleList.printTimeTable();
            break;
        case EXIT:
            Ui.printExitMessage();
            StorageEncoder.encodeAndSaveSemesterListToJson(NoCap.semesterList);
            this.isExit = true;
            break;
        case MODULETYPE:
            moduleParser(taskDescription);
            StorageEncoder.encodeAndSaveSemesterListToJson(NoCap.semesterList);
            break;
        default:
            Ui.printInvalidInputMessage();
            break;
        }
    }


    /**
     * First separate the input into two parts. The first part is saved as module.
     * The next part is split again to obtain the new taskType and taskDescription
     *
     * @param input String to be separated
     */
    void moduleParser(String input) {
        splitInput(input);
        try {
            module = NoCap.moduleList.find(taskType.toUpperCase(Locale.ROOT));
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printInvalidModuleNameMessage();
            return;
        }

        if (isEmptyDescription(taskDescription)) {
            return;
        }
        splitInput(taskDescription);

        Task selectedTask;
        switch (taskType) {
        case LIST:
            list.moduleListParser(module,taskDescription);
            break;
        case ADDCLASS:
            if (isEmptyDescription(taskDescription)) {
                break;
            }
            module.addClass(taskDescription);
            Ui.addModuleClassMessage(module);
            break;
        case ADDTASK:
            if (isEmptyDescription(taskDescription) | !hasDateDescription(taskDescription)) {
                break;
            }
            module.addTask(taskDescription);
            break;
        case ADDGRADABLE:
            if (isEmptyDescription(taskDescription) | !hasDateDescription(taskDescription)
                    | !hasWeightageDescription(taskDescription)) {
                break;
            }
            module.addGradableTask(taskDescription);
            break;
        case DONE:
            if (isEmptyDescription(taskDescription)) {
                break;
            }
            selectedTask = parserSearch.getTaskFromIndex(taskDescription, module.taskList.getTaskList());
            if (selectedTask != null) {
                selectedTask.markDone();
            }
            break;
        case NOTDONE:
            if (isEmptyDescription(taskDescription)) {
                break;
            }
            selectedTask = parserSearch.getTaskFromIndex(taskDescription, module.taskList.getTaskList());
            if (selectedTask != null) {
                selectedTask.markNotDone();
            }
            break;
        case ADDGRADE:
            if (isEmptyDescription(taskDescription)) {
                break;
            }
            module.addGrade(taskDescription);
            Ui.addModuleGradeMessage(module);
            NoCap.semester.updateCap();
            NoCap.semesterList.updateCap();
            break;
        case ADDCREDIT:
            if (isEmptyDescription(taskDescription)) {
                break;
            }
            module.addCredits(Integer.parseInt(taskDescription));
            Ui.addModuleCreditsMessage(module);
            NoCap.semester.updateCap();
            NoCap.semesterList.updateCap();
            break;
        case DELETECLASS:
            module.deleteClass();
            break;
        case DELETETASK:
            if (isEmptyDescription(taskDescription)) {
                break;
            }
            selectedTask = parserSearch.getTaskFromKeyword(taskDescription, module.taskList.getTaskList());
            if (selectedTask != null) {
                module.deleteTask(selectedTask);
            }
            break;
        case EDITDESCRIPTION:
            if (isEmptyDescription(taskDescription)) {
                break;
            }
            splitInput(taskDescription);
            if (isEmptyDescription(taskDescription)) {
                break;
            }
            selectedTask = parserSearch.getTaskFromIndex(taskType, module.taskList.getTaskList());
            if (selectedTask != null) {
                selectedTask.setDescription(taskDescription);
            }
            break;
        case EDITDEADLINE:
            if (isEmptyDescription(taskDescription)) {
                break;
            }
            splitInput(taskDescription);
            if (isEmptyDescription(taskDescription)) {
                break;
            }
            selectedTask = parserSearch.getTaskFromIndex(taskType, module.taskList.getTaskList());
            if (selectedTask != null) {
                selectedTask.setDate(taskDescription);
            }
            break;
        case DELETEGRADE:
            module.deleteGrade();
            NoCap.semester.updateCap();
            NoCap.semesterList.updateCap();
            break;
        case INFO:
            module.showInformation();
            break;
        default:
            Ui.printInvalidInputMessage();
            break;
        }
    }

    //split string on first space
    static void splitInput(String input) {
        try {
            int typePos = input.indexOf(SPACE_STRING);
            taskType = input.substring(0, typePos);
            taskDescription = input.substring(typePos).trim();
        } catch (StringIndexOutOfBoundsException e) {
            taskType = input.trim();
            taskDescription = EMPTY_STRING;
            assert (taskType.equals(input.trim()));
        }
    }

    public boolean isExit() {
        return this.isExit;
    }

    public String getTaskType() {
        return taskType;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    /**
     * Used in add to verify module does not exist. Prevent duplicate module entries.
     *
     * @param input moduleName to be checked against.
     * @return true if input is existing module.
     */
    boolean isDuplicateModule(String input) {
        try {
            module = NoCap.moduleList.find(input.toUpperCase(Locale.ROOT));
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
        Ui.duplicateModuleError();
        return true;
    }

    boolean isEmptyDescription(String input) {
        if (input.isEmpty()) {
            Ui.missingDescription();
            return true;
        }
        return false;
    }

    boolean hasDateDescription(String input) {
        if (input.contains(START_OF_DATE)) {
            return true;
        }
        Ui.invalidDate();
        return false;
    }

    boolean isNotInteger(String input) {
        if (input == null) {
            Ui.inputNotInteger();
            return true;
        }
        try {
            int in = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            Ui.inputNotInteger();
            return true;
        }
        return false;
    }

    boolean hasWeightageDescription(String input) {
        int typePos = input.indexOf(START_OF_DATE);
        String secondPart = input.substring(typePos);
        if (secondPart.contains(START_OF_WEIGHTAGE)) {
            return true;
        }
        Ui.invalidWeightage();
        return false;
    }

}