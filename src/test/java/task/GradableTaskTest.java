package task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GradableTaskTest {

    @Test
    void getWeightage() {
        GradableTask gradableTask = new GradableTask("Assignment", "23/11/2012 1600", 30);
        assertEquals(30, gradableTask.getWeightage());
    }

    @Test
    void testToString() {
        GradableTask gradableTask = new GradableTask("Assignment", "23/11/2012 1600", 30);
        String expected = "Assignment by: 23 Nov 2012 04:00 PM Weightage 30% [ ]";
        assertEquals(expected, gradableTask.toString());
    }
}