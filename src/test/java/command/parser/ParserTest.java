package command.parser;

import org.junit.jupiter.api.Test;
import task.TaskList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void splitInput_withDescription_success() {
        Parser testParser = new Parser();
        testParser.chooseTask("Add taskDescription");
        assertEquals("Add", testParser.getTaskType());
        assertEquals("taskDescription", testParser.getTaskDescription());
    }

    @Test
    public void splitInput_withoutDescription_success() {
        Parser testParser = new Parser();
        testParser.chooseTask("Help");
        assertEquals("Help", testParser.getTaskType());
        assertEquals("", testParser.getTaskDescription());
    }

    @Test
    public void getTaskFromKeyword_keywordNotFound_printError() {
        TaskList a = new TaskList();
        a.addTask("cs1010", "Read Book A /by 12/12/2021 1600");
        a.addTask("cs1010", "Read Book B /by 13/12/2021 1600");
    }

}
