package wework.contact;

import com.alibaba.fastjson.JSON;
import io.restassured.response.Response;

import java.io.IOException;
import java.util.HashMap;

public class Member extends Contact {

    public Response create(HashMap<String, Object> map) throws Exception {
        String body=templateFromYaml("data/createmember.yml",map);
        Reset();
        return requestSpecification.body(body)
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/user/create")
                .then().log().all().extract().response();
    }

    public Response getUser(String userId) {
        Reset();
        return requestSpecification
                .queryParam("userid",userId)
                .when().get("https://qyapi.weixin.qq.com/cgi-bin/user/get")
                .then().log().all().extract().response();
    }

    public Response updateUser(HashMap<String, Object> map) throws IOException {
        Reset();
        String body=templateFromYaml("data/UpdateMember",map);
        return  requestSpecification
                .body(body)
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/user/update")
                .then().log().all().extract().response();
    }

    public Response listUser(String departmentId, String fetchchild){
        Reset();
        return  requestSpecification
                .param("department_id",departmentId)
                .param("fetch_child",fetchchild)
                .when().get("https://qyapi.weixin.qq.com/cgi-bin/user/list")
                .then().log().all().extract().response();
    }

    public Response userSimplelist(String departmentId, String fetchchild){
        Reset();
        return  requestSpecification
                .param("department_id",departmentId)
                .param("fetch_child",fetchchild)
                .when().get("https://qyapi.weixin.qq.com/cgi-bin/user/simplelist")
                .then().log().all().extract().response();
    }

    public Response userDelete(String userId){
        Reset();
        return requestSpecification
                .queryParam("userid",userId)
                .when().get("https://qyapi.weixin.qq.com/cgi-bin/user/delete")
                .then().log().all().extract().response();
    }

    public Response batchDelete(HashMap<String, Object> map){
        String body = JSON.toJSONString(map);
        Reset();
        return requestSpecification.body(body)
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/user/batchdelete")
                .then().log().all().extract().response();
    }

    public Response invite(HashMap<String, Object> map){
        String body = JSON.toJSONString(map);
        return requestSpecification.body(body)
                .when().get("https://qyapi.weixin.qq.com/cgi-bin/batch/invite")
                .then().log().all().extract().response();
    }
}

