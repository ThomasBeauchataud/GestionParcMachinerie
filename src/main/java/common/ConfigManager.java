package common;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class ConfigManager {

    public static final String applicationName = "GestionParcMachinerie";
    public static final String casUrl = "http://localhost:36878/web_war_exploded/";
    public static String applicationPath;

    private static InputStream inputStream;
    private static Yaml yaml;

    public static void init(String url) {
        if(applicationPath == null) {
            applicationPath = url;
            yaml = new Yaml();
            inputStream = ConfigManager.class.getClassLoader()
                    .getResourceAsStream(applicationPath + "CONFIG/config.yaml");
        }
    }

    public static Object get(String key) {
        Map<String, Object> data = yaml.load(inputStream);
        return data.get(key);
    }

}
