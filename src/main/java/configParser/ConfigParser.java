package configParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConfigParser {
    private final String absolutePath = "/Users/decagon/IdeaProjects/fileReadingTask2/src/main/resources/config-files/config.txt";
    public static Map<String, String> configurations = new HashMap<>();
    private final Map<String, String> allPaths = new HashMap<>();

    public ConfigParser(String filePath) {
        this.putInMap();
        File file = new File(getPathValue(filePath));
        this.readFile(file);
    }

    private void readFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String eof;
            String app = "";
            while ((eof = reader.readLine()) != null) {
                if (eof.startsWith("[") && eof.endsWith("]")) {
                    app = eof.substring(1,(eof.length() -1)) + ".";
                }
                if (eof.contains("=")) {
                    String[] data = eof.split("=");
                    data[0] = app + data[0];
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
        this.allPaths.put("production",this.absolutePath);
        this.allPaths.put("development",this.absolutePath + ".dev");
        this.allPaths.put("staging",this.absolutePath + ".staging");
    }

    private String getPathValue(String path){
        if(path.equals("") || path.equals("production") ) return this.allPaths.get("production");
        if(this.allPaths.containsKey(path)) return this.allPaths.get(path);
        return this.absolutePath;
    }

}
