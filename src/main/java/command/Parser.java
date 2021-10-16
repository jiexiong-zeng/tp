package command;

import command.storage.StorageEncoder;
import module.Module;

import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Parser {

    private static final String EMPTY_STRING = "";
    private static final String SPACE_STRING = " ";
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
    public static final String ADDGRADE = "addgrade";
    public static final String DELETECLASS = "deleteclass";
    public static final String DELETETASK = "deletetask";
    public static final String DELETEGRADE = "deletegrade";
    public static final String DONE = "done";
    public static final String INFO = "info";
    public static final String START_OF_DATE = "/by";
    public static final String SORT_BY_DATE = "sortbydate";
    public static final String SORT_BY_STATUS = "sortbystatus";
    public static final String SHOW_WEEK = "w";
    public static final String SHOW_MONTH = "m";
    public static final String SHOW_YEAR = "y";

    static String taskType;
    static String taskDescription;
    private final List list = new List();
    protected String moduleName;
    protected Module module;
    protected boolean isExit;
    private static Logger logger = Logger.getLogger(Parser.class.getName());


    public Parser() {
        this.isExit = false;
        logger.setLevel(Level.OFF);
    }

    /**
     * Method to process user command.
     *
     * @param line User input
     */
    public void chooseTask(String line) {
        splitInput(line);
        switch (taskType) {
        case HELP:
            Ui.printHelpMessage();
            logger.log(Level.INFO, "Help Test");
            break;
        case ADD:
            if (taskDescription.isEmpty()) {
                Ui.missingDescription();
                break;
            }
            if (isDuplicateModule(taskDescription)) {
                Ui.duplicateModuleError();
                break;
            }
            NoCap.moduleList.add(taskDescription.toUpperCase(Locale.ROOT));
            StorageEncoder.encodeAndSaveModuleListToJson(NoCap.moduleList);
            logger.log(Level.INFO, "Add Test");
            break;
        case DELETE:
            if (taskDescription.isEmpty()) {
                Ui.missingDescription();
                break;
            }
            NoCap.moduleList.delete(taskDescription);
            StorageEncoder.encodeAndSaveModuleListToJson(NoCap.moduleList);
            logger.log(Level.INFO, "Delete Test");
            break;
        case LIST:
            list.listParser(taskDescription);
            break;
        case TIMETABLE:
            NoCap.moduleList.printTimeTable();
            logger.log(Level.INFO, "Timetable Test");
            break;
        case EXIT:
            Ui.printExitMessage();
            logger.log(Level.INFO, "Exit Test");
            StorageEncoder.encodeAndSaveModuleListToJson(NoCap.moduleList);
            this.isExit = true;
            break;
        case MODULETYPE:
            moduleParser(taskDescription);
            StorageEncoder.encodeAndSaveModuleListToJson(NoCap.moduleList);
            break;
        default:
            System.out.println("Invalid Input!");
            break;
        }
    }

    /**
     * First separate the input into two parts. The first part is saved as moduleName.
     * The next part is split again to obtain the new taskType and taskDescription
     *
     * @param input String to be separated
     */
    void moduleParser(String input) {

        splitInput(input);
        moduleName = taskType.toUpperCase(Locale.ROOT);
        try {
            module = NoCap.moduleList.find(moduleName);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("invalid Module name!");
            return;
        }

        if (taskDescription.isEmpty()) {
            Ui.missingDescription();
            return;
        }
        splitInput(taskDescription);

        switch (taskType) {
        case ADDCLASS:
            if (taskDescription.isEmpty()) {
                Ui.missingDescription();
                break;
            }
            module.addClass(taskDescription);
            logger.log(Level.INFO, "AddClass test");
            break;
        case ADDTASK:
            if (taskDescription.isEmpty()) {
                Ui.missingDescription();
                break;
            }
            if (!taskDescription.contains(START_OF_DATE)) {
                Ui.invalidDate();
                break;
            }
            module.addTask(taskDescription);
            break;
        case DONE:
            try {
                if (taskDescription.isBlank()) {
                    Ui.printInvalidIndex();
                    break;
                }
                int index = Integer.parseInt(taskDescription) - 1;
                if (index < 0) {
                    Ui.printInvalidIndex();
                    break;
                }
                module.taskList.get(index).markDone();
                Ui.printMarkDoneMessage(module.taskList.get(index));
            } catch (IndexOutOfBoundsException e) {
                Ui.printInvalidIndex();
            }
            break;
        case ADDGRADE:
            if (taskDescription.isEmpty()) {
                Ui.missingDescription();
                break;
            }
            module.addGrade(taskDescription);
            logger.log(Level.INFO, "AddGrade test");
            break;
        case DELETECLASS:
            module.deleteClass();
            logger.log(Level.INFO, "DeleteClass test");
            break;
        case DELETETASK:
            module.deleteTask(module.getTaskList().get(Integer.parseInt(taskDescription)));
            logger.log(Level.INFO, "DeleteTask test");
            break;
        case DELETEGRADE:
            module.deleteGrade();
            logger.log(Level.INFO, "DeleteGrade test");
            break;
        case INFO:
            module.showInformation();
            break;
        default:
            System.out.println("Invalid Module Command!");
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

    public String getModuleName() {
        return this.moduleName;
    }

    public String getTaskType() {
        return taskType;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    void listParser(String input) {
        list.listParser(input);
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
        return true;
    }
}
