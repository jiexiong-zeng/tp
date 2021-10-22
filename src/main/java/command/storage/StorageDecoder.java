package command.storage;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import command.Ui;
import semester.SemesterList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StorageDecoder {

    //Constants
    private static final String ROOT = System.getProperty("user.dir");
    private static final Path FILE_PATH = Paths.get(ROOT, "data", "data.json");

    //Logger object
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

<<<<<<< HEAD
    public static ModuleList decodeJsonToModuleList() {
        logger.setLevel(Level.OFF);
        ObjectMapper objectMapper = new ObjectMapper();
        ModuleList modules = new ModuleList();
        if (!Files.exists(FILE_PATH)) {
            Ui.printNoSaveFileMessage();
            assert modules.getModuleList().size() == 0;
            return modules;
        }
=======
    public static SemesterList decodeJsonToSemesterList() {
        ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.registerModule(new JavaTimeModule());
        SemesterList semesters = new SemesterList();
>>>>>>> bf5859bcb1871d8b2b8e71a5a49648b6094e179f
        try {
            if (!Files.exists(FILE_PATH)) {
                Ui.printNoSaveFileMessage();
                assert semesters.toString().equals(new SemesterList().toString());
                return semesters;
            }
            semesters = objectMapper.readValue(new File(FILE_PATH.toString()), SemesterList.class);
            Ui.loadFileSuccessful();
            logger.log(Level.INFO, "Load file successful");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Error reading save file, creating new template");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return semesters;
    }
}
