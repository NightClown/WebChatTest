package utils;

import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.Map;

public class YamlUtil {

    public static Map readYmlFile(String path) throws IOException {
        Yaml yaml = new Yaml();
        InputStream inputStream= YamlUtil.class.getClassLoader().getResourceAsStream(path);
        BufferedInputStream bis = new BufferedInputStream(inputStream);
        Map map = yaml.loadAs(bis, Map.class);
        return map;
    }

    public static <T> void dumpConf(String save, T obj) throws IOException {
        Yaml yaml = new Yaml();
        yaml.dump(obj, new BufferedWriter(new FileWriter(save)));
    }
}