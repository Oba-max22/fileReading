import configParser.ConfigParser;

public class Main {
    public static void main(String[] args) {
        ConfigParser config = new ConfigParser("staging");

        String dbName = config.get("dbname");
        System.out.println(dbName);

        String stagingDbname = config.get("application.name");
        System.out.println(stagingDbname);

        String mode = config.get("application.mode");
        System.out.println(mode);

        String theme = config.get("application.theme");
        System.out.println(theme);

        String pipeline = config.get("application.pipeline");
        System.out.println(pipeline);

    }
}
