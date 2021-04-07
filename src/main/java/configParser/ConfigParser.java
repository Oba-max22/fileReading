package configParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConfigParser {
    private final String contentRootPath = "src/main/resources/config-files/config.txt";
    public static Map<String, String> configurations = new HashMap<>();
    private final Map<String, String> allPaths = new HashMap<>();

    public ConfigParser(String name_of_file) {
        File file = new File(getPathValue(name_of_file));
        this.readFile(file);
    }

    private void readFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line_of_file;
            String app_key_prefix = "";
            while ((line_of_file = reader.readLine()) != null) {
                if (line_of_file.startsWith("[") && line_of_file.endsWith("]")) {
                    app_key_prefix = line_of_file.substring(1,(line_of_file.length() -1)) + ".";
                }
                if (line_of_file.contains("=")) {
                    String[] data = line_of_file.split("=");
                    data[0] = app_key_prefix + data[0];
                    if (configurations.containsKey(data[0]))continue;
                    configurations.put(data[0], data[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String get(String key){
        return configurations.getOrDefault(key, "Invalid key");
    }

    private void putInMap(){
        this.allPaths.put("production",this.contentRootPath);
        this.allPaths.put("development",this.contentRootPath + ".dev");
        this.allPaths.put("staging",this.contentRootPath + ".staging");
    }

    private String getPathValue(String path){
        this.putInMap();
        if(path.equals("") || path.equals("production") ) return this.allPaths.get("production");
        if(this.allPaths.containsKey(path)) return this.allPaths.get(path);
        return this.contentRootPath;
    }

}
