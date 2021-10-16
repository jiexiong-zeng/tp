package module;

import command.storage.StorageDecoder;

import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModuleList {
    private static final Logger logger = Logger.getLogger(StorageDecoder.class.getName());

    private static final int HEIGHT = 33;
    private static final int LENGTH = 207;
    private static final int BOXWIDTH = 20;
    private static final int BOXHEIGHT = 5;
    private ArrayList<Module> moduleList;

    public ModuleList() {
        logger.setLevel(Level.OFF);
        this.moduleList = new ArrayList<>();
    }

    //Getters
    public ArrayList<Module> getModuleList() {
        return moduleList;
    }

    //Setters
    public void setModuleList(ArrayList<Module> moduleList) {
        this.moduleList = moduleList;
    }

    public void add(Module module) {
        this.moduleList.add(module);
    }

    //overloading to take in String input * Added by jiexiong to keep Parser clean
    public void add(String input) {
        Module module = new Module(input);
        this.moduleList.add(module);
        logger.log(Level.INFO,"Module added successfully");
    }

    public void delete(Module module) {
        moduleList.remove(module);
    }

    //overloading to take in String input * Added by jiexiong to keep Parser clean
    public void delete(String input) {
        int moduleIndex = Integer.parseInt(input) - 1;
        moduleList.remove(get(moduleIndex));
        logger.log(Level.INFO,"Module deleted successfully");
    }

    public int size() {
        return this.moduleList.size();
    }

    public Module get(int index) {
        assert index >= 0;
        return this.moduleList.get(index);
    }

    public Module find(String input) {
        String moduleName = input.trim();
        int index = -1;
        for (int i = 0; i < moduleList.size(); i++) {
            if (Objects.equals(moduleList.get(i).moduleName, moduleName)) {
                index = i;
            }
        }
        if (index == -1) {
            throw new ArrayIndexOutOfBoundsException("Unable to find task");
        }
        logger.log(Level.INFO,"Module found successfully");
        return moduleList.get(index);
    }

    public void printModules() {
        for (int i = 0; i < moduleList.size(); i++) {
            System.out.println(i + 1);
            System.out.println(moduleList.get(i));
        }
    }

    private String formatTimeString(int time) {
        String timeString;
        assert (time < 24 && time >= 0);
        if (time < 10) {
            timeString = "0" + time + "00";
        } else {
            timeString = time + "00";
        }
        assert timeString.length() == 4;
        return timeString;
    }

    private int moduleAtTime(String timeString, int r) {
        int moduleIndex = -1;
        String day;
        if (r <= 6) {
            day = "MON";
        } else if (r <= 11) {
            day = "TUE";
        } else if (r <= 16) {
            day = "WED";
        } else if (r <= 21) {
            day = "THU";
        } else if (r <= 26) {
            day = "FRI";
        } else {
            day = "SAT";
        }
        for (int i = 0; i < moduleList.size(); i++) {
            for (int j = 0; j < moduleList.get(i).size(); j++) {
                if (Objects.equals(moduleList.get(i).get(j).getDay(), day)
                        && Objects.equals(moduleList.get(i).get(j).getStartTime(), timeString)) {
                    moduleIndex = i;
                    break;
                }
            }
        }
        return moduleIndex;
    }

    private int classAtTime(String timeString, int r) {
        int classIndex = 0;
        String day;
        day = dayOfRow(r);
        for (Module module : moduleList) {
            for (int j = 0; j < module.size(); j++) {
                if (Objects.equals(module.get(j).getDay(), day)
                        && Objects.equals(module.get(j).getStartTime(), timeString)) {
                    classIndex = j;
                    break;
                }
            }
        }
        return classIndex;
    }

    private String dayOfRow(int r) {
        String day;
        if (r >= 3 && r <= 6) {
            day = "MON";
        } else if (r >= 8 && r <= 11) {
            day = "TUE";
        } else if (r >= 13 && r <= 16) {
            day = "WED";
        } else if (r >= 18 && r <= 21) {
            day = "THU";
        } else if (r >= 23 && r <= 26) {
            day = "FRI";
        } else {
            day = "SAT";
        }
        return day;
    }

    public void printTimeTable() {
        for (int r = 0; r < HEIGHT; r++) {
            for (int c = 0; c < LENGTH; c++) {
                if ((c + 12) % BOXWIDTH == 0) {
                    int time = ((c + 12) / BOXWIDTH) + 7;
                    String timeString = formatTimeString(time);
                    c = printTimeColumns(r, c, timeString);
                } else if ((r + 2) % BOXHEIGHT == 0 && c == 2) { //print day
                    c = printRowDay(r, c);
                } else if (c == LENGTH - 1) {
                    System.out.println("#");
                } else if ((r - 2) % BOXHEIGHT == 0 || r == 0 || r == HEIGHT - 1 || (c - 6) % BOXWIDTH == 0 || c == 0) {
                    System.out.print("#");
                } else {
                    System.out.print(" ");
                }
            }
        }
        logger.log(Level.INFO,"Timetable printed successfully");
    }

    private int printTimeColumns(int r, int c, String timeString) {
        if (r == 1) { // print time header
            System.out.print(timeString);
            c += 4;
        } else if ((r + 2) % BOXHEIGHT == 0) { //print module name if class exists in timeslot
            c = printSlotModuleName(r, c, timeString);
        } else if ((r + 1) % BOXHEIGHT == 0) { //print class location
            c = printSlotClassLocation(r, c, timeString);
        } else if (r > 0 && r % BOXHEIGHT == 0) { //print class comment
            c = printSlotClassComment(r, c, timeString);
        }
        return c;
    }

    private int printRowDay(int r, int c) {
        int day = (r + 2) / BOXHEIGHT;
        switch (day) {
        case 1:
            System.out.print("MON");
            break;
        case 2:
            System.out.print("TUE");
            break;
        case 3:
            System.out.print("WED");
            break;
        case 4:
            System.out.print("THU");
            break;
        case 5:
            System.out.print("FRI");
            break;
        case 6:
            System.out.print("SAT");
            break;
        default:
            System.out.print("INVALID");
        }
        c += 2;
        return c;
    }

    private int printSlotClassComment(int r, int c, String timeString) {
        int moduleIndex = moduleAtTime(timeString, r);
        int classIndex = classAtTime(timeString, r);
        if (moduleIndex >= 0) {
            System.out.print(moduleList.get(moduleIndex).get(classIndex).getComment());
            c += moduleList.get(moduleIndex).get(classIndex).getComment().length();
        }
        return c;
    }

    private int printSlotClassLocation(int r, int c, String timeString) {
        int moduleIndex = moduleAtTime(timeString, r);
        int classIndex = classAtTime(timeString, r);
        if (moduleIndex >= 0) {
            System.out.print(moduleList.get(moduleIndex).get(classIndex).getLocation());
            c += moduleList.get(moduleIndex).get(classIndex).getLocation().length();
        }
        return c;
    }

    private int printSlotModuleName(int r, int c, String timeString) {
        int moduleIndex = moduleAtTime(timeString, r);
        if (moduleIndex >= 0) {
            System.out.print(moduleList.get(moduleIndex).moduleName);
            c += moduleList.get(moduleIndex).moduleName.length();
        }
        return c;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < moduleList.size(); i++) {
            output.append(i + 1).append(System.lineSeparator())
                    .append(moduleList.get(i)).append(System.lineSeparator());
        }
        return output.toString();
    }

}
