package wework.contact;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;


class DepartmentTest {
    Department department;
    @BeforeEach
    void setUp() {
        if(department==null){
            department=new Department();
        }
    }

    @Test
    void list() {
        department.list("1").then().statusCode(200).body("department.name[0]",equalTo("NBA集团公司"));
    }

    @Test
    void create(){
        department.create("洛杉矶湖人", 1).then().body("errcode",equalTo(0));
    }

    @Test
    void update(){
        department.update("休斯顿火箭",3).then().body("errmsg",equalTo("updated"));
        department.list("4").then().statusCode(200).body("department.name[0]",equalTo("休斯顿火箭")) ;
    }


    @Test
    void delete(){
        department.delete("2").then().body("errmsg",equalTo("deleted"));
    }
}