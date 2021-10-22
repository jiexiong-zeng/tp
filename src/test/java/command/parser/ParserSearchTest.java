package command.parser;

import org.junit.jupiter.api.Test;
import task.TaskList;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ParserSearchTest {

    ParserSearch testParserSearch = new ParserSearch();

    @Test
    public void getTaskFromIndex_invalidIndex_returnNull() {
        TaskList a = new TaskList();
        a.addTask("cs1010", "Read Book A /by 12/12/2021 1600");
        a.addTask("cs1010", "Read Book B /by 13/12/2021 1600");
        assertNull(testParserSearch.getTaskFromIndex("0", a.getTaskList()));
        assertNull(testParserSearch.getTaskFromIndex("3", a.getTaskList()));
        assertNull(testParserSearch.getTaskFromIndex("abc", a.getTaskList()));
    }

    @Test
    public void getTaskFromIndex_validIndex_returnTask() {
        TaskList a = new TaskList();
        a.addTask("cs1010", "Read Book A /by 12/12/2021 1600");
        a.addTask("cs1010", "Read Book B /by 13/12/2021 1600");
        assertEquals(testParserSearch.getTaskFromIndex("1", a.getTaskList()), a.get(0));
        assertEquals(testParserSearch.getTaskFromIndex("2", a.getTaskList()), a.get(1));
    }

    @Test
    public void getTaskFromKeyword_keywordNotFound_returnNull() {
        TaskList a = new TaskList();
        a.addTask("cs1010", "Read Book A /by 12/12/2021 1600");
        a.addTask("cs1010", "Read Book B /by 13/12/2021 1600");
        assertNull(testParserSearch.getTaskFromKeyword("xyz", a.getTaskList()));
    }

    @Test
    public void getTaskFromKeyword_EmptyTaskList_returnNull() {
        TaskList a = new TaskList();
        assertNull(testParserSearch.getTaskFromKeyword("xyz", a.getTaskList()));
    }

    @Test
    public void getTaskFromKeyword_MatchOne_returnMatch() {
        TaskList a = new TaskList();
        a.addTask("cs1010", "Read Book A /by 12/12/2021 1600");
        a.addTask("cs1010", "Read Book B /by 13/12/2021 1600");
        String input = "1";
        InputStream in;

        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertEquals(testParserSearch.getTaskFromKeyword("Book A", a.getTaskList()), a.get(0));
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertEquals(testParserSearch.getTaskFromKeyword("Book B", a.getTaskList()), a.get(1));
    }

    @Test
    public void getTaskFromKeyword_MatchBoth_returnIndexChoice() {
        TaskList a = new TaskList();
        a.addTask("cs1010", "Read Book A /by 12/12/2021 1600");
        a.addTask("cs1010", "Read Book B /by 13/12/2021 1600");
        String input;
        InputStream in;

        input = "1";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertEquals(testParserSearch.getTaskFromKeyword("Book", a.getTaskList()), a.get(0));

        input = "2";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertEquals(testParserSearch.getTaskFromKeyword("Book", a.getTaskList()), a.get(1));
    }

}
