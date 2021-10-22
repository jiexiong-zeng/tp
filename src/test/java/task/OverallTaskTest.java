package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OverallTaskTest {
    @Test
    void overallTask_normalTask_success() {
        Task task = new Task("Assignment", "19/09/2021 1600");
        OverallTask overallTask = new OverallTask(task, "CS2113");
        assertEquals("[CS2113][ ][ ] Assignment by: 19 Sep 2021 04:00 PM ", overallTask.toString());
    }

    @Test
    void overallTask_gradableTask_success() {
        GradableTask task = new GradableTask("Quiz", "19/09/2021 1600", 50);
        OverallTask overallTask = new OverallTask(task, "CS2113");
        assertEquals("[CS2113][G][ ] Quiz by: 19 Sep 2021 04:00 PM [Weightage: 50%]", overallTask.toString());
    }

    @Test
    void dateComparator_equal_success() {
        Task task1 = new Task("Assignment", "19/09/2021 1600");
        OverallTask overallTask1 = new OverallTask(task1, "CS2113");
        GradableTask task2 = new GradableTask("Quiz", "19/09/2021 1600", 50);
        OverallTask overallTask2 = new OverallTask(task2, "CS2113");
        int result = OverallTask.dateComparator.compare(overallTask1, overallTask2);
        assertEquals(result, 0);
    }

    @Test
    void dateComparator_leftHeavy_success() {
        Task task1 = new Task("Assignment", "19/09/2021 1600");
        OverallTask overallTask1 = new OverallTask(task1, "CS2113");
        GradableTask task2 = new GradableTask("Quiz", "19/09/2021 1800", 50);
        OverallTask overallTask2 = new OverallTask(task2, "CS2113");
        int result = OverallTask.dateComparator.compare(overallTask1, overallTask2);
        assertTrue(result < 0);
    }

    @Test
    void dateComparator_rightHeavy_success() {
        Task task1 = new Task("Assignment", "19/09/2021 1800");
        OverallTask overallTask1 = new OverallTask(task1, "CS2113");
        GradableTask task2 = new GradableTask("Quiz", "19/09/2021 1600", 50);
        OverallTask overallTask2 = new OverallTask(task2, "CS2113");
        int result = OverallTask.dateComparator.compare(overallTask1, overallTask2);
        assertTrue(result > 0);
    }

    @Test
    void statusComparator_equal_success() {
        Task task1 = new Task("Assignment", "19/09/2021 1600");
        OverallTask overallTask1 = new OverallTask(task1, "CS2113");
        GradableTask task2 = new GradableTask("Quiz", "19/09/2021 1600", 50);
        OverallTask overallTask2 = new OverallTask(task2, "CS2113");
        int result = OverallTask.statusComparator.compare(overallTask1, overallTask2);
        assertEquals(result, 0);
    }

    @Test
    void statusComparator_leftHeavy_success() {
        Task task1 = new Task("Assignment", "19/09/2021 1600");
        task1.markDone();
        OverallTask overallTask1 = new OverallTask(task1, "CS2113");
        GradableTask task2 = new GradableTask("Quiz", "19/09/2021 1600", 50);
        OverallTask overallTask2 = new OverallTask(task2, "CS2113");
        int result = OverallTask.statusComparator.compare(overallTask1, overallTask2);
        assertTrue(result < 0);
    }

    @Test
    void statusComparator_rightHeavy_success() {
        Task task1 = new Task("Assignment", "19/09/2021 1600");
        OverallTask overallTask1 = new OverallTask(task1, "CS2113");
        GradableTask task2 = new GradableTask("Quiz", "19/09/2021 1600", 50);
        task2.markDone();
        OverallTask overallTask2 = new OverallTask(task2, "CS2113");
        int result = OverallTask.statusComparator.compare(overallTask1, overallTask2);
        assertTrue(result > 0);
    }


}