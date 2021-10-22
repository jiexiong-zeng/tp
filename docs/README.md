# User Guide

NoCap (NC) is a **desktop app for managing modules taken in NUS, optimized for use via a Command Line Interface** (CLI). If you can type fast, NC can get your module management tasks done faster than traditional GUI apps. It is the perfect app for NUS students!

* Features
    * Viewing help : `help`
    * Adding a module: `add <module>`
    * Deleting a module : `delete <module>`
    * Adding tasks for a module : `/m <module> addtask <description>`
    * Adding grade for a module : `/m <module> addgrade <grade letter>`
    * Adding classes for a module : `/m <module> addclass <description> /on <day> /at <period>`
    * Deleting tasks for a module : `/m <module> deletetask`
    * Deleting grade for a module : `/m <module> deletegrade`
    * Deleting classes for a module : `/m <module> deleteclass `
    * Show mod info : `/m <module> info`
    * Show timetable : `timetable`
    * Show modules : `list module`
    * Show tasks : `list task`
    * Exit : `exit`

    

# Quick start

1. Ensure you have Java `11` or above installed in your Computer. If you are unsure, follow this link: [https://java.com/en/download/help/version_manual.html](https://java.com/en/download/help/version_manual.html). If you do not have Java 11 installed you can download the latest version of java from this link: [https://www.oracle.com/java/technologies/downloads/#java11](https://www.oracle.com/java/technologies/downloads/#java11)
2. Download the latest version of NoCap from this link here : <span style="text-decoration:underline;">link</span>
3. Copy the file to the folder you want to use as the _home directory _for your NoCap.
4. Open Command prompt and change directory to the home directory for NoCap using the `cd` command.
5. Launch NoCap using the command `java NoCap`
6. Type the command in the command box and press Enter to execute it. e.g. typing `help` and pressing Enter will open the help window. \
   Some example commands you can try:
    * `timetable` Shows timetable for the semester
    * `'list` task Shows all the tasks what you have
    * `/m <module name> info` Shows all module related information
7. Refer to the <span style="color:blue">Features</span> below for details of each command.


# Features

### **Command format:**

* Words in `UPPER_CASE` are the parameters to be supplied by the user. \
  e.g. `add MODULENAME` MODULENAME is the name of the module supplied by the user.
* Items in curved brackets describe the input format. \
  e.g.` MODULENAME addtask DESCRIPTION /by DATE(DDMMYY)` DDMMYY refers to the date-month-year of the description.
* Parameters must be in the exact order as seen in the user guide. \
  e.g. if the command specifies `CS2113 addclass tutorial /on Wednesday /at 23:00, CS2113 addclass /at 23:00 tutorial /on Wednesday` is not acceptable.
* Extraneous parameters for commands that do not take in parameters (such as `help`, `timetable `and `exit`) is not acceptable. \
  e.g. if the command specifies `help 123`, there will be an error.

## Viewing help : `help`
Shows a message explaining how to use NoCap  
Format: `help`

## Adding module: `add <module>`
Adds a module  
Format: `add MODULENAME`  
Examples:
* `add CS2113T`
* `add MA1508`

## Delete module: `delete <module>`
Deletes a module
Format: `delete MODULE`
Examples:
* `delete CS2101`
* `delete CS2113T`

## Add class : `/m <module> addclass`
Adds a class to a module  
Format: `MODULENAME addclass DAY/STARTIME/LOCATON/COMMENTS`  
Examples:
* `CS2113 addclass MON/0800/E4-02/LAB`
* `MA1508 addclass WED/1000/ZOOM/TUTORIAL`

## Add task : `/m <module> addtask `
Adds a task to a module  
Format: `/m MODULENAME addtask DESCRIPTION /by DATE(DDMMYY)`  
Examples:
* `/m CS2113 addtask finish tutorial hwk /by 030321`
* `/m MA1508 addtask do assignment 4 /by 240921`

## Add grade : `/m <module> addgrade `
Adds a grade to a module   
Format: `/m MODULENAME addgrade DESCRIPTION`  
Examples:
* `/m CS2113 addgrade A`
* `/m MA1508 addgrade B-`

## Add credit : `/m <module> addcredit`
Adds a credit to a module   
Format: `/m MODULENAME addcredit DESCRIPTION`  
Examples:
* `/m CS2113 addcredit 4`
* `/m CG1111 addcredit 6`

## Delete classes: `/m <module> deleteclass`
Deletes all classes from a module  
Format: `/m MODULENAME deleteclass`  
Example:
* `/m CS2113 deleteclass`

## Delete tasks: `/m <module> deletetask`
Deletes all tasks from a module  
Format: `/m MODULENAME deletetask`  
Example:
* `/m CS2113 deletetask`

## Delete grade: `/m <module> deletegrade`
Deletes grade from a module   
Format: `/m MODULENAME deletegrade`  
Examples:
* `/m CS2113 deletegrade`

## Listing tasks : `list task`
Shows a list of all tasks   
Format: `list task`

## Listing module : `list module`
Shows a list of all modules   
Format: `list module`

## Show module information : `/m <module> info`
Prints out information regarding the module. Information includes undone tasks, class information and grades attained so far.   
Format: `/m MODULE info`  
Examples:
* `/m CS1010 info`
```
**EXAMPLE**
```

## Show timetable : `timetable`
Prints out timetable.   
Format: `timetable`
```
**OUTPUT EXAMPLE**
```

## Exiting the program : `exit`
Exits the program.   
Format: `exit`

