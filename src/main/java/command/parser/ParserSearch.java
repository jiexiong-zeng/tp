package command.parser;

import command.Ui;
import task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class ParserSearch {

    public ParserSearch() {
    }

    public Task getTaskFromIndex(String input, ArrayList<Task> taskList) {
        int index;
        Task task = null;
        try {
            index = Integer.parseInt(input) - 1;
            if (isValidIndex(index)) {
                task = taskList.get(index);
            }
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            Ui.printInvalidIndex();
        }
        return task;
    }

    boolean isValidIndex(int index) {
        if (index < 0) {
            Ui.printInvalidIndex();
            return false;
        }
        return true;
    }

    public Task getTaskFromKeyword(String keyword, ArrayList<Task> allTaskList) {
        Task task = null;
        ArrayList<Task> taskList = new ArrayList<Task>();
        for (Task item : allTaskList) {
            if (item.getDescription().contains(keyword)) {
                taskList.add(item);
            }
        }

        if (taskList.isEmpty()) {
            Ui.printInvalidKeyword();
            return task;
        }
        assert (!taskList.isEmpty());

        Ui.printTaskFound();
        int counter = 1;
        for (Task item : taskList) {
            System.out.println(counter + ": " + item.getDescription());
            counter++;
        }

        Ui.printSelectIndex();
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        task = getTaskFromIndex(input, taskList);
        return task;
    }
}