import configParser.ConfigParser;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ConfigParser config = new ConfigParser("");
        System.out.println(config.get("dbname"));
        System.out.println(config.get("application.name"));
        System.out.println(config.get("application.port"));
        System.out.println(" ");

        ConfigParser configDev = new ConfigParser("development");
        System.out.println(configDev.get("dbname"));
        System.out.println(configDev.get("application.name"));
        System.out.println(configDev.get("application.port"));
        System.out.println(" ");

        ConfigParser configStg = new ConfigParser("staging");
        System.out.println(configStg.get("dbname"));
        System.out.println(configStg.get("application.name"));
        System.out.println(configStg.get("application.port"));
        System.out.println(" ");

    }
}
