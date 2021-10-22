package command.storage;

import module.Module;
import module.ModuleList;
import schedule.Schedule;
import org.junit.jupiter.api.Test;
import semester.SemesterList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


class StorageEncoderTest {

    private static final String ROOT = System.getProperty("user.dir");
    private static final Path FILE_PATH = Paths.get(ROOT, "data", "data.json");
    private static final Path DIRECTORY_PATH = Paths.get(ROOT, "data");

    @Test
    public void encodeModuleList_normalModuleList_success() {
        SemesterList semesters = new SemesterList();
        ModuleList modules = semesters.extractAccessedSemester().getModuleList();
        modules.add(new Module("CS2102"));
        modules.add(new Module("CS2112"));
        modules.add(new Module("CS2132"));
        modules.get(0).addTask("sleep /by 21/08/2022 1600");
        modules.get(0).addCredits(4);
        modules.get(2).addClass(new Schedule("Monday", "12pm", "E3", "Bad"));
        modules.get(0).addClass(new Schedule("Monday", "12pm", "D3", "Bad"));
        modules.get(0).addClass(new Schedule("Tuesday", "12pm", "D3", "Bad"));
        StorageEncoder.encodeAndSaveSemesterListToJson(semesters);
        assertTrue(Files.exists(FILE_PATH));
        try {
            String fileContent = Files.readString(FILE_PATH);
            String expectedString = "{\n"
                    + "  \"credits\" : 0,\n"
                    + "  \"points\" : 0.0,\n"
                    + "  \"cap\" : 0.0,\n"
                    + "  \"accessedSemesterIndex\" : 0,\n"
                    + "  \"semesterList\" : [ {\n"
                    + "    \"credits\" : 0,\n"
                    + "    \"points\" : 0.0,\n"
                    + "    \"cap\" : 0.0,\n"
                    + "    \"semester\" : \"Y1S1\",\n"
                    + "    \"moduleList\" : {\n"
                    + "      \"moduleList\" : [ {\n"
                    + "        \"letterGrade\" : null,\n"
                    + "        \"points\" : 0.0,\n"
                    + "        \"moduleName\" : \"CS2102\",\n"
                    + "        \"taskList\" : {\n"
                    + "          \"taskList\" : [ {\n"
                    + "            \"description\" : \"sleep\",\n"
                    + "            \"date\" : \"21/08/2022 1600\",\n"
                    + "            \"deadline\" : [ 2022, 8, 21, 16, 0 ],\n"
                    + "            \"done\" : false\n"
                    + "          } ],\n"
                    + "          \"taskCount\" : 1\n"
                    + "        },\n"
                    + "        \"scheduleList\" : {\n"
                    + "          \"scheduleList\" : [ {\n"
                    + "            \"startTime\" : \"12pm\",\n"
                    + "            \"location\" : \"D3\",\n"
                    + "            \"day\" : \"Monday\",\n"
                    + "            \"comment\" : \"Bad\"\n"
                    + "          }, {\n"
                    + "            \"startTime\" : \"12pm\",\n"
                    + "            \"location\" : \"D3\",\n"
                    + "            \"day\" : \"Tuesday\",\n"
                    + "            \"comment\" : \"Bad\"\n"
                    + "          } ]\n"
                    + "        },\n"
                    + "        \"credits\" : 4,\n"
                    + "        \"gradableTaskList\" : {\n"
                    + "          \"taskList\" : [ ],\n"
                    + "          \"taskCount\" : 0,\n"
                    + "          \"gradableTaskList\" : [ ]\n"
                    + "        }\n"
                    + "      }, {\n"
                    + "        \"letterGrade\" : null,\n"
                    + "        \"points\" : 0.0,\n"
                    + "        \"moduleName\" : \"CS2112\",\n"
                    + "        \"taskList\" : {\n"
                    + "          \"taskList\" : [ ],\n"
                    + "          \"taskCount\" : 0\n"
                    + "        },\n"
                    + "        \"scheduleList\" : {\n"
                    + "          \"scheduleList\" : [ ]\n"
                    + "        },\n"
                    + "        \"credits\" : 0,\n"
                    + "        \"gradableTaskList\" : {\n"
                    + "          \"taskList\" : [ ],\n"
                    + "          \"taskCount\" : 0,\n"
                    + "          \"gradableTaskList\" : [ ]\n"
                    + "        }\n"
                    + "      }, {\n"
                    + "        \"letterGrade\" : null,\n"
                    + "        \"points\" : 0.0,\n"
                    + "        \"moduleName\" : \"CS2132\",\n"
                    + "        \"taskList\" : {\n"
                    + "          \"taskList\" : [ ],\n"
                    + "          \"taskCount\" : 0\n"
                    + "        },\n"
                    + "        \"scheduleList\" : {\n"
                    + "          \"scheduleList\" : [ {\n"
                    + "            \"startTime\" : \"12pm\",\n"
                    + "            \"location\" : \"E3\",\n"
                    + "            \"day\" : \"Monday\",\n"
                    + "            \"comment\" : \"Bad\"\n"
                    + "          } ]\n"
                    + "        },\n"
                    + "        \"credits\" : 0,\n"
                    + "        \"gradableTaskList\" : {\n"
                    + "          \"taskList\" : [ ],\n"
                    + "          \"taskCount\" : 0,\n"
                    + "          \"gradableTaskList\" : [ ]\n"
                    + "        }\n"
                    + "      } ]\n"
                    + "    }\n"
                    + "  }, {\n"
                    + "    \"credits\" : 0,\n"
                    + "    \"points\" : 0.0,\n"
                    + "    \"cap\" : 0.0,\n"
                    + "    \"semester\" : \"Y1S2\",\n"
                    + "    \"moduleList\" : {\n"
                    + "      \"moduleList\" : [ ]\n"
                    + "    }\n"
                    + "  }, {\n"
                    + "    \"credits\" : 0,\n"
                    + "    \"points\" : 0.0,\n"
                    + "    \"cap\" : 0.0,\n"
                    + "    \"semester\" : \"Y2S1\",\n"
                    + "    \"moduleList\" : {\n"
                    + "      \"moduleList\" : [ ]\n"
                    + "    }\n"
                    + "  }, {\n"
                    + "    \"credits\" : 0,\n"
                    + "    \"points\" : 0.0,\n"
                    + "    \"cap\" : 0.0,\n"
                    + "    \"semester\" : \"Y2S2\",\n"
                    + "    \"moduleList\" : {\n"
                    + "      \"moduleList\" : [ ]\n"
                    + "    }\n"
                    + "  }, {\n"
                    + "    \"credits\" : 0,\n"
                    + "    \"points\" : 0.0,\n"
                    + "    \"cap\" : 0.0,\n"
                    + "    \"semester\" : \"Y3S1\",\n"
                    + "    \"moduleList\" : {\n"
                    + "      \"moduleList\" : [ ]\n"
                    + "    }\n"
                    + "  }, {\n"
                    + "    \"credits\" : 0,\n"
                    + "    \"points\" : 0.0,\n"
                    + "    \"cap\" : 0.0,\n"
                    + "    \"semester\" : \"Y3S2\",\n"
                    + "    \"moduleList\" : {\n"
                    + "      \"moduleList\" : [ ]\n"
                    + "    }\n"
                    + "  }, {\n"
                    + "    \"credits\" : 0,\n"
                    + "    \"points\" : 0.0,\n"
                    + "    \"cap\" : 0.0,\n"
                    + "    \"semester\" : \"Y4S1\",\n"
                    + "    \"moduleList\" : {\n"
                    + "      \"moduleList\" : [ ]\n"
                    + "    }\n"
                    + "  }, {\n"
                    + "    \"credits\" : 0,\n"
                    + "    \"points\" : 0.0,\n"
                    + "    \"cap\" : 0.0,\n"
                    + "    \"semester\" : \"Y4S2\",\n"
                    + "    \"moduleList\" : {\n"
                    + "      \"moduleList\" : [ ]\n"
                    + "    }\n"
                    + "  }, {\n"
                    + "    \"credits\" : 0,\n"
                    + "    \"points\" : 0.0,\n"
                    + "    \"cap\" : 0.0,\n"
                    + "    \"semester\" : \"Y5S1\",\n"
                    + "    \"moduleList\" : {\n"
                    + "      \"moduleList\" : [ ]\n"
                    + "    }\n"
                    + "  }, {\n"
                    + "    \"credits\" : 0,\n"
                    + "    \"points\" : 0.0,\n"
                    + "    \"cap\" : 0.0,\n"
                    + "    \"semester\" : \"Y5S2\",\n"
                    + "    \"moduleList\" : {\n"
                    + "      \"moduleList\" : [ ]\n"
                    + "    }\n"
                    + "  } ]\n"
                    + "}";
            String expectedOutput = expectedString.replaceAll("\n", System.lineSeparator());
            assertEquals(expectedOutput, fileContent);
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void encodeSemesterList_emptySemesterList_success() {
        SemesterList semesters = new SemesterList();
        StorageEncoder.encodeAndSaveSemesterListToJson(semesters);
        assertTrue(Files.exists(FILE_PATH));
        try {
            String fileContent = Files.readString(FILE_PATH);
            String expectedString = "{\n"
                    + "  \"credits\" : 0,\n"
                    + "  \"points\" : 0.0,\n"
                    + "  \"cap\" : 0.0,\n"
                    + "  \"accessedSemesterIndex\" : 0,\n"
                    + "  \"semesterList\" : [ {\n"
                    + "    \"credits\" : 0,\n"
                    + "    \"points\" : 0.0,\n"
                    + "    \"cap\" : 0.0,\n"
                    + "    \"semester\" : \"Y1S1\",\n"
                    + "    \"moduleList\" : {\n"
                    + "      \"moduleList\" : [ ]\n"
                    + "    }\n"
                    + "  }, {\n"
                    + "    \"credits\" : 0,\n"
                    + "    \"points\" : 0.0,\n"
                    + "    \"cap\" : 0.0,\n"
                    + "    \"semester\" : \"Y1S2\",\n"
                    + "    \"moduleList\" : {\n"
                    + "      \"moduleList\" : [ ]\n"
                    + "    }\n"
                    + "  }, {\n"
                    + "    \"credits\" : 0,\n"
                    + "    \"points\" : 0.0,\n"
                    + "    \"cap\" : 0.0,\n"
                    + "    \"semester\" : \"Y2S1\",\n"
                    + "    \"moduleList\" : {\n"
                    + "      \"moduleList\" : [ ]\n"
                    + "    }\n"
                    + "  }, {\n"
                    + "    \"credits\" : 0,\n"
                    + "    \"points\" : 0.0,\n"
                    + "    \"cap\" : 0.0,\n"
                    + "    \"semester\" : \"Y2S2\",\n"
                    + "    \"moduleList\" : {\n"
                    + "      \"moduleList\" : [ ]\n"
                    + "    }\n"
                    + "  }, {\n"
                    + "    \"credits\" : 0,\n"
                    + "    \"points\" : 0.0,\n"
                    + "    \"cap\" : 0.0,\n"
                    + "    \"semester\" : \"Y3S1\",\n"
                    + "    \"moduleList\" : {\n"
                    + "      \"moduleList\" : [ ]\n"
                    + "    }\n"
                    + "  }, {\n"
                    + "    \"credits\" : 0,\n"
                    + "    \"points\" : 0.0,\n"
                    + "    \"cap\" : 0.0,\n"
                    + "    \"semester\" : \"Y3S2\",\n"
                    + "    \"moduleList\" : {\n"
                    + "      \"moduleList\" : [ ]\n"
                    + "    }\n"
                    + "  }, {\n"
                    + "    \"credits\" : 0,\n"
                    + "    \"points\" : 0.0,\n"
                    + "    \"cap\" : 0.0,\n"
                    + "    \"semester\" : \"Y4S1\",\n"
                    + "    \"moduleList\" : {\n"
                    + "      \"moduleList\" : [ ]\n"
                    + "    }\n"
                    + "  }, {\n"
                    + "    \"credits\" : 0,\n"
                    + "    \"points\" : 0.0,\n"
                    + "    \"cap\" : 0.0,\n"
                    + "    \"semester\" : \"Y4S2\",\n"
                    + "    \"moduleList\" : {\n"
                    + "      \"moduleList\" : [ ]\n"
                    + "    }\n"
                    + "  }, {\n"
                    + "    \"credits\" : 0,\n"
                    + "    \"points\" : 0.0,\n"
                    + "    \"cap\" : 0.0,\n"
                    + "    \"semester\" : \"Y5S1\",\n"
                    + "    \"moduleList\" : {\n"
                    + "      \"moduleList\" : [ ]\n"
                    + "    }\n"
                    + "  }, {\n"
                    + "    \"credits\" : 0,\n"
                    + "    \"points\" : 0.0,\n"
                    + "    \"cap\" : 0.0,\n"
                    + "    \"semester\" : \"Y5S2\",\n"
                    + "    \"moduleList\" : {\n"
                    + "      \"moduleList\" : [ ]\n"
                    + "    }\n"
                    + "  } ]\n"
                    + "}";
            String expectedOutput = expectedString.replaceAll("\n", System.lineSeparator());
            assertEquals(expectedOutput, fileContent);
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void encodeModuleList_noFile_success() {
        try {
            if (Files.exists(DIRECTORY_PATH)) {
                Files.delete(FILE_PATH);
                Files.delete(DIRECTORY_PATH);
            }
        } catch (IOException e) {
            fail(e.getMessage());
        }
        SemesterList semesters = new SemesterList();
        StorageEncoder.encodeAndSaveSemesterListToJson(semesters);
        assertTrue(Files.exists(FILE_PATH));
    }
}