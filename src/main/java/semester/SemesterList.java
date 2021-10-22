package semester;

import java.util.ArrayList;

public class SemesterList {
    protected int credits;
    protected double points;
    protected double cap;
    protected int accessedSemesterIndex;
    private ArrayList<Semester> semesterList;

    public SemesterList() {
        this.accessedSemesterIndex = 0;
        this.credits = 0;
        this.points = 0;
        this.cap = 0;
        this.semesterList = new ArrayList<>();
        setUp();
    }

    //Getters fo Json serialization and deserialization
    public int getCredits() {
        return credits;
    }

    public double getPoints() {
        return points;
    }

    public ArrayList<Semester> getSemesterList() {
        return semesterList;
    }

    private void setUp() {
        for (int i = 0; i < 10; i++) {
            int year = (i / 2) + 1;
            int sem = (i % 2) + 1;
            String semesterDescription = "Y" + year + "S" + sem;
            add(semesterDescription);
        }
    }

    private void add(Semester semester) {
        this.semesterList.add(semester);
    }

    private void add(String semesterDescription) {
        Semester semester = new Semester(semesterDescription);
        this.semesterList.add(semester);
    }

    public void printSemesters() {
        int i = 1;
        for (Semester semester : semesterList) {
            System.out.println(i + " : " + semester.getSemester());
            i++;
        }
    }

    public Semester extractAccessedSemester() {
        return this.semesterList.get(getAccessedSemesterIndex());
    }

    private void updateCredits() {
        int c = 0;
        for (Semester semester : semesterList) {
            semester.updateCredits();
            c += semester.getCredits();
        }
        credits = c;
    }

    private void updatePoints() {
        double p = 0;
        for (Semester semester : semesterList) {
            semester.updatePoints();
            p += semester.getPoints();
        }
        points = p;
    }

    public void updateCap() {
        updateCredits();
        updatePoints();
        cap = points / credits;
    }

    public void setAccessedSemesterIndex(int i) {
        this.accessedSemesterIndex = i;
    }

    public double getCap() {
        return cap;
    }

    public int getAccessedSemesterIndex() {
        return accessedSemesterIndex;
    }

    public Semester get(int index) {
        assert index >= 0;
        return this.semesterList.get(index);
    }

    public void visualiseCap() {
        updateCap();
        System.out.print("Cumulative CAP: " + getCap());
        for (Semester semester : semesterList) {
            semester.updateCap();
            System.out.println(semester.getSemester() + ": " + semester.getCap());
        }
        /*for (int i = 0; i < 35; i++) {
            for (int j = 0; j < 50; j++) {
                if (j < 3) {
                    if (i >= 5 && i <= 30 && i % 5 == 0) {
                        System.out.print((6 - (5/ i)) + ".00");
                    } else {
                        System.out.print("    ");
                    }
                    j += 3;
                }
                if (j == 4 && i <= 30 || i == 30 && j > 4) {
                    System.out.print("#");
                }
                if (i == 31 && j % 5 == 0) {
                    //print semester label
                }

            }
        }*/

    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("Cumulative Credits: ").append(this.credits).append(System.lineSeparator())
                .append("Points: ").append(this.points).append(System.lineSeparator())
                .append("CAP: ").append(this.cap).append(System.lineSeparator())
                .append("Semesters: ").append(System.lineSeparator());
        int semesterCount = 1;
        for (Semester semester : semesterList) {
            string.append(semesterCount).append(". ").append(System.lineSeparator())
                    .append(semester).append(System.lineSeparator());
        }
        return string.toString();
    }
}
