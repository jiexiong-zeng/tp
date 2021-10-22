package module;

import schedule.Schedule;
import task.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ModuleTest {

    @Test
    void getLetterGrade() {
        Module m = new Module("cs2113T");
        m.addGrade("A+");
        assertEquals("A+", m.getLetterGrade());
    }

    @Test
    void getModuleName() {
        Module m = new Module("cs2113T");
        assertEquals("cs2113T", m.getModuleName());
    }

    @Test
    void getTaskList() {
        Module m = new Module("cs2113T");
        m.addTask("read /by 1800");
        TaskList taskList = new TaskList();
        taskList.addTask("cs2113T", "read /by 1800");
        assertEquals(taskList.toString(), m.getTaskList().toString());
    }

    @Test
    void getScheduleList() {
        Module m = new Module("cs2113T");
        m.addClass("WED/0800/E1-01/tut");
        assertEquals(new Schedule("WED", "0800", "E1-01", "tut").toString(), m.get(0).toString());
    }

    @Test
    void getCredits() {
        Module m = new Module("cs2113T");
        m.addCredits(4);
        assertEquals(4, m.getCredits());
    }

    @Test
    void size() {
        Module m = new Module("cs2113T");
        m.addClass("WED/0800/E1-01/tut");
        m.addClass("TUE/0800/E1-01/lect");
        m.addClass("MON/1000/E1-01/lect");
        assertEquals(3, m.size());
    }

    @Test
    void testToString() {
        Module m = new Module("cs2113T");
        m.addGrade("A+");
        m.addCredits(4);
        m.addClass("WED/0800/E1-01/tut");
        m.addClass("TUE/0800/E1-01/lect");
        m.addClass("MON/1000/E1-01/lect");
        m.addTask("read /by 11/11/2021 1900");
        m.addTask("do tutorial /by 12/11/2021 2000");
        m.addGradableTask("Assignment /by 12/12/2021 1600 /w 30");
        m.addGradableTask("Finals /by 15/12/2021 1600 /w 50");
        String exp = "Module name: cs2113T\n"
                + "CREDITS: 4\n" + "--------------------------- \n" + "SCHEDULE: \n" + "1.\n"
                + "Day: WED\n" + "Start Time: 0800\n" + "Location: E1-01\n" + "Comments: tut\n" + "2.\n"
                + "Day: TUE\n" + "Start Time: 0800\n" + "Location: E1-01\n" + "Comments: lect\n" + "3.\n"
                + "Day: MON\n" + "Start Time: 1000\n" + "Location: E1-01\n" + "Comments: lect\n"
                + "--------------------------- \n" + "GRADE: A+\n"
                + "TASKS: [[ ] read by: 11 Nov 2021 07:00 PM, [ ] do tutorial by: 12 Nov 2021 08:00 PM]\n"
                + "BREAKDOWN: \n1.\n" + "Assignment by: 12 Dec 2021 04:00 PM Weightage 30% [ ]\n"
                + "2.\n" + "Finals by: 15 Dec 2021 04:00 PM Weightage 50% [ ]\n";
        assertEquals(exp, m.toString());
    }
}