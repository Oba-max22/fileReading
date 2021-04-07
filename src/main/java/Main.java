import configParser.ConfigParser;

public class Main {
    public static void main(String[] args) {
        final String contentRootPath = "src/main/resources/config-files/config.txt";
        String[] environment =  {".dev", ".staging"};
        String filename = "";

        if (args.length == 0 || args[0].equalsIgnoreCase("production")) {
            filename = contentRootPath + "";
        } else if (args[0].equalsIgnoreCase("development")) {
            filename = contentRootPath + environment[0];
        } else if (args[0].equalsIgnoreCase("staging")) {
            filename = contentRootPath + environment[1];
        }

        ConfigParser config = new ConfigParser(filename);

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

