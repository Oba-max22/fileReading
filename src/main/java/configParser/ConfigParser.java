package configParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConfigParser {
    //Map for storing config file data
    public static Map<String, String> configurations = new HashMap<>();

    /**
     * Constructor
     * @param name_of_file
     */
    public ConfigParser(String name_of_file) {
        //Create an instance of the File class
        File file = new File(name_of_file);
        //call readFile method and pass in file instance as argument
        this.readFile(file);
    }

    /**
     * readFile method reads the file line by line and stores data in a Map
     * @param file
     */
    private void readFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line_of_file;
            String app_key_prefix = "";
            while ((line_of_file = reader.readLine()) != null) {

                //This code block helps to get "application" from [application]
                if (line_of_file.startsWith("[") && line_of_file.endsWith("]")) {
                    app_key_prefix = line_of_file.substring(1,(line_of_file.length() -1)) + ".";
                }

                //This block helps to split the data by the equal to operator
                if (line_of_file.contains("=")) {
                    String[] data = line_of_file.split("=");

                    //This appends "application" to the keys that come after application line
                    data[0] = app_key_prefix + data[0];
                    if (configurations.containsKey(data[0])) {
                        continue;
                    }

                    configurations.put(data[0], data[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *  This method returns the map key or a default value
     * @param key
     * @return
     */
    public String get(String key){
        return configurations.getOrDefault(key, "Invalid key");
    }
}
