package wework.contact;

import io.restassured.http.ContentType;
import wework.Restful;
import wework.Wework;

import static io.restassured.RestAssured.given;

public class Contact extends Restful {
    String random=String.valueOf(System.currentTimeMillis());
    public Contact(){
        Reset();
    }

    public void Reset(){
        requestSpecification=given()
                .log().all()
                .queryParam("access_token", Wework.getToken())
                .contentType(ContentType.JSON);
    }
}