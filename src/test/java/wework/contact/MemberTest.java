package wework.contact;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;

class MemberTest {
    static Member member;
    static ResponseSpecification responseSpecification;
    @BeforeAll
    static void setUp() {
        member=new Member();
        responseSpecification = new ResponseSpecBuilder().build();
        responseSpecification.statusCode(200);
        responseSpecification.body("errcode",equalTo(0));
    }
    /*创建成员*/
    @ParameterizedTest
    @ValueSource(strings = { "哈登", "保罗", "卡佩拉"})
    void create(String name) throws Exception {
        String nameNew= RandomStringUtils.randomAlphanumeric(6);
        String random=String.valueOf(System.currentTimeMillis()).substring(5+0, 5+8);
        HashMap<String, Object> map=new HashMap<>();
        map.put("userid", nameNew);
        map.put("name", name);
        map.put("department", Arrays.asList(1, 4));
        map.put("mobile", "151"+ random);
        map.put("email", random + "@qq.com");
        member.create(map).then().spec(responseSpecification);
    }

    /*读取成员*/
    @Test
    void getUser() {
        member.getUser("2f409b").then().spec(responseSpecification)
        .body("name",equalTo("哈登"));
    }
    /*更新成员*/
    @Test
    void updateUser() throws IOException {
        HashMap<String, Object> map=new HashMap<>();
        map.put("userid", "2f409b");
        map.put("name","MacGrady");
        member.updateUser(map).then().spec(responseSpecification);
    }

    /*获取部门成员详情*/
    @Test
    void userList(){
        member.listUser("4","1").then().spec(responseSpecification);
    }

    /*获取部门成员*/
    @Test
    void userSimplelist(){
        member.userSimplelist("4","1").then().spec(responseSpecification);

    }

    /*删除成员*/
    @Test
    void userDelete(){
        member.userDelete("2f409b").then().spec(responseSpecification);
    }

    /*批量删除成员*/
    @Test
    void batchDelete(){
        List<String> list = member.userSimplelist("4","1").body().path("userlist.userid");
        HashMap<String, Object> map=new HashMap<>();
        map.put("useridlist",list);
        member.batchDelete(map).then().spec(responseSpecification);
    }

    /*邀请成员*/
    @Test
    void invited(){
        List<String> list = member.userSimplelist("2","1").body().path("userlist.userid");
        HashMap<String, Object> map=new HashMap<>();
        map.put("user",list);
        member.invite(map).then().statusCode(200).body("errorcode",equalTo(0));
    }

}