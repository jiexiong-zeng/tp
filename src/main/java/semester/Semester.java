package semester;

import module.ModuleList;
import module.Module;


public class Semester {
    protected int credits;
    protected double points;
    protected double cap;
    protected String semester;
    public ModuleList moduleList;

    public Semester(String semester) {
        this.semester = semester;
        this.credits = 0;
        this.points = 0;
        this.cap = 0;
        this.moduleList = new ModuleList();
    }

    /**
     * For deserialization from JSON file.
     */
    public Semester() {
    }

    protected void updateCredits() {
        int c = 0;
        for (Module module : moduleList.getModuleList()) {
            c += module.getCredits();
        }
        credits = c;
    }

    protected void updatePoints() {
        double p = 0;
        for (Module module : moduleList.getModuleList()) {
            p += module.getCredits() * module.getPoints();
        }
        points = p;
    }

    public void updateCap() {
        updateCredits();
        updatePoints();
        cap = points / credits;
    }

    public int getCredits() {
        return credits;
    }

    public double getPoints() {
        return points;
    }

    public double getCap() {
        return cap;
    }

    public ModuleList getModuleList() {
        return this.moduleList;
    }

    public String getSemester() {
        return this.semester;

    }

    @Override
    public String toString() {
        return "Credits: " + this.credits + System.lineSeparator()
                + "Points: " + this.points + System.lineSeparator()
                + "CAP: " + (this.cap) + System.lineSeparator()
                + "Semester: " + this.semester + System.lineSeparator()
                + "Modules: " + this.moduleList + System.lineSeparator();
    }
}
