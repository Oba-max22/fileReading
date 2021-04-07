package configParser;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConfigParserTest {

    ConfigParser config = new ConfigParser("src/main/resources/config-files/config.txt");

    @Test
     void get() {
        assertEquals("sq04_db", config.get("dbname"));
        assertEquals("fintek", config.get("application.name"));
    }
}