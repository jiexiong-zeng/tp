package task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;


class GradableTaskListTest extends TaskListTest {

    @Test
    void getGradableTaskList() {
        GradableTaskList gradableTaskList = new GradableTaskList();
        gradableTaskList.addGradableTask("cs1010", "Assignment /by 12/12/2021 1600 /w 30");
        gradableTaskList.addGradableTask("cs1010", "Finals /by 12/12/2021 1600 /w 50");
        GradableTask g1 = new GradableTask("Assignment", "12/12/2021 1600", 30);
        GradableTask g2 = new GradableTask("Finals", "12/12/2021 1600", 50);
        assertEquals(g1.toString(), gradableTaskList.getGradableTask(0).toString());
        assertEquals(g2.toString(), gradableTaskList.getGradableTask(1).toString());
    }

    @Test
    void testToString() {
        GradableTaskList gradableTaskList = new GradableTaskList();
        gradableTaskList.addGradableTask("cs1010", "Assignment /by 12/12/2021 1600 /w 30");
        gradableTaskList.addGradableTask("cs1010", "Finals /by 12/12/2021 1600 /w 50");
        String expected = "1.\n" + "Assignment by: 12 Dec 2021 04:00 PM Weightage 30% [ ]\n"
                + "2.\n" + "Finals by: 12 Dec 2021 04:00 PM Weightage 50% [ ]\n";
        assertEquals(expected, gradableTaskList.toString());
    }
}