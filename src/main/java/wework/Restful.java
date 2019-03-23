package wework;

import com.alibaba.fastjson.JSON;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.restassured.specification.RequestSpecification;
import utils.YamlUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Restful {
    HashMap<String,Object> query=new HashMap<>();
    public RequestSpecification requestSpecification = given().log().all();

    public static String template(String path, HashMap<String,Object> map){
        DocumentContext documentContext = JsonPath.parse(Restful.class.getResourceAsStream(path));
        map.entrySet().forEach(entry ->{
            documentContext.set(entry.getKey(), entry.getValue());
        } );
        return  documentContext.jsonString();
    }

    public static String templateFromYaml(String path, HashMap<String, Object> map) throws IOException {
        Map context = YamlUtil.readYmlFile(path);
        String yamlStr= JSON.toJSONString(context);
        DocumentContext documentContext =JsonPath.parse(yamlStr);
        map.entrySet().forEach(entry ->{
            documentContext.set(entry.getKey(), entry.getValue());
        } );
        return documentContext.jsonString();
    }


}