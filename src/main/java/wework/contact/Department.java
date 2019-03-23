package wework.contact;

import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;

public class Department extends Contact{
    public Response list(String id){
        Reset();
        return requestSpecification
                .param("id",id)
                .when().get("https://qyapi.weixin.qq.com/cgi-bin/department/list")
                .then().log().all().extract().response();

    }

    public Response create(String name,Integer parentid){
        Reset();
        String body= JsonPath.parse(this.getClass().getResourceAsStream("/data/creat.json"))
                .set("$.name",name)
                .set("parentid",parentid).jsonString();
        return  requestSpecification
                .body(body)
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
                .then().log().all().extract().response();
    }

    public Response update(String name,Integer id){
        Reset();
        String body= JsonPath.parse(this.getClass().getResourceAsStream("/data/update.json"))
                .set("$.name",name)
                .set("$.id",id).jsonString();
        return requestSpecification
                .body(body)
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/department/update")
                .then().log().all().extract().response();
    }

    public Response delete(String id){
        Reset();
        return requestSpecification
                .param("id",id)
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
                .then().log().all().extract().response();
    }

}