package utils;

import org.junit.jupiter.api.Test;

import java.util.Map;

class YamlUtilTest {

    @Test
    void loadConf() throws Exception {
        Map configMap = YamlUtil.readYmlFile("data/createmember.yml");
        System.out.println(configMap);
    }
}