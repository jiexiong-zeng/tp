package task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TaskTest {
    @Test
    void markDone_success() {
        Task task = new Task("Submit Assignment 1", "19/09/2021 1600");
        task.markDone();
        assertTrue(task.isDone());
    }

    @Test
    void getStatusIcon_success() {
        Task task = new Task("Submit Assignment 1", "19/09/2021 1600");
        task.markDone();
        task.createStatusIcon();
        assertEquals("[X]", task.createStatusIcon());
    }

    @Test
    void getDescription_success() {
        Task task = new Task("Submit Assignment 1", "19/09/2021 1600");
        assertEquals("Submit Assignment 1", task.getDescription());
    }

    @Test
    void getDate_success() {
        Task task = new Task("Submit Assignment 1", "19/09/2021 1600");
        assertEquals("19/09/2021 1600", task.getDate());
    }

    @Test
    void setDescription_success() {
        Task task = new Task("Submit Assignment 1", "19/09/2021 1600");
        task.setDescription("Review Assignment 1");
        assertEquals("Review Assignment 1", task.getDescription());
    }

    @Test
    void setDone_success() {
        Task task = new Task("Submit Assignment 1", "19/09/2021 1600");
        task.markDone();
        task.setDone(false);
        assertFalse(task.isDone());
    }

    @Test
    void setDate_success() {
        Task task = new Task("Submit Assignment 1", "20/09/2021 1600");
        task.setDate("20th October 2021");
        assertEquals("20th October 2021", task.getDate());
    }
}
