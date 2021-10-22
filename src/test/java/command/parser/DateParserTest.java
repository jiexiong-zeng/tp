package command.parser;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateParserTest {
    @Test
    public void parseDate_variousFormat_success() {
        assertEquals("2020-12-01T00:00", DateParser.parseDate("2020-12-01").toString());
        assertEquals("2020-12-01T18:12", DateParser.parseDate("01 12 2020 18:12").toString());
        assertEquals("2020-12-01T18:12", DateParser.parseDate("01 12 2020 1812").toString());
        assertEquals("2020-12-01T00:00", DateParser.parseDate("01 12 2020").toString());
        assertEquals("2020-12-01T00:00", DateParser.parseDate("01 12 20").toString());
        assertEquals("2020-12-01T18:12", DateParser.parseDate("01/12/2020 18:12").toString());
        assertEquals("2020-12-01T18:12", DateParser.parseDate("01/12/2020 1812").toString());
        assertEquals("2020-12-01T00:00", DateParser.parseDate("01/12/2020").toString());
        assertEquals("2021-12-01T00:00", DateParser.parseDate("01/12/21").toString());
    }

    @Test
    public void dateStringOutput_success() {
        LocalDateTime testCase = DateParser.parseDate("01/12/20");
        assertEquals("1 Dec 2020 12:00 AM", DateParser.dateStringOutput(testCase));
    }

}
