# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}
<br>
<br>
Third party libraries:
- [Jackson Databind](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind)
- [Jackson Datatype-jsr310](https://mvnrepository.com/artifact/com.fasterxml.jackson.datatype/jackson-datatype-jsr310)
- [Jackson Annotations](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-annotations)

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}


## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}



-
# [**Acknowledgements**](https://se-education.org/addressbook-level3/DeveloperGuide.html#acknowledgements)
-
# [**Setting up, getting started**](https://se-education.org/addressbook-level3/DeveloperGuide.html#setting-up-getting-started)
-
# [**Design**](https://se-education.org/addressbook-level3/DeveloperGuide.html#design)

-
# [Architecture](https://se-education.org/addressbook-level3/DeveloperGuide.html#architecture)
-
# [Parser component](https://se-education.org/addressbook-level3/DeveloperGuide.html#ui-component)
-
# [Storage component](https://se-education.org/addressbook-level3/DeveloperGuide.html#logic-component)
-
# [semester component](https://se-education.org/addressbook-level3/DeveloperGuide.html#model-component)
-
# [module component](https://se-education.org/addressbook-level3/DeveloperGuide.html#storage-component)
-
# [schedule component](https://se-education.org/addressbook-level3/DeveloperGuide.html#common-classes)
-
# [task component]
-
# [**Implementation**](https://se-education.org/addressbook-level3/DeveloperGuide.html#implementation)


# Semester

**API** : `semester`

The Semester component stores all NoCap data i.e., all Semester objects and cummula average point (CAP) (which are contained in a SemesterList object)



* It consists of 2 utility classes SemesterList and Semester
* SemesterList is used to compute and store the cumulative CAP of all semesters and also stores 10 fixed Semester objects
* Each Semester object stores and computes the individual CAP for the semester, while also storing a moduleList of the modules taken during the semester
* The computation of the CAP for both SemesterList and Semester is automatically done when a grade/credit as added to a module within any semester

# Module List

**API** : module

Data from all the modules are stored in the ModuleList class. This includes:

1. moduleName
2. letterGrade
3. credits
4. points
5. TaskList
6. GradableTaskList
7. ScheduleList

The modules are stored in an ArrayList and ModuleList uses the Module.get(int index) method to access the target Module.

- ModuleList is responsible for printing the Time Table. It accesses different schedules of different mods before constructing a Time Table.
- ModuleList contains getter method find(String input) which returns a module by the same name as the input.

How printing a timetable works:

1. ModuleList first extracts day of week and timeslot information from different schedules.
2. It then prints out the Timetable one line at a time. At the same time it checks if the day of week and the timeslot corresponds to the schedule.

- If day of week and timeslot corresponds, venue and comments information is printed out
- If day of week and timeslot does not correspond, and blank character &quot; &quot; is printed instead.

# ScheduleList

**API** : schedule

ScheduleList consists of all data for the schedule for the module.

This includes:

day of week

timeslot

venue

comments

How ScheduleList works:

1. An empty ScheduleList is created when a module is constructed.
2. When addClass is called in module , ScheduleList parses the input from the user and splits the information into the relevant information. The information is then used to generate a new instance of Schedule which is then added to the list.
3. toString() prints out all relevant schedule information in a list format. This is done by going through the list and printing Schedule one after another.

Notes about ScheduleList

- ScheduleList checks that the input for the day of the week is only from the list of possible days: MON, TUE, WED, THU, FRI, SAT ,SUN. All other inputs will result in an exception being thrown.
- When a new Schedule class is called, ScheduleList ensures that the length of venue and comments are less than 16 characters in length. This is to ensure that it fits within its time slot within the Timetable when printed.
