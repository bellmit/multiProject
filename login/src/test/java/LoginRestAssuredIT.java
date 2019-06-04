import domain.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class LoginRestAssuredIT {
    static User user ;

    String deliveryId = "";
    public LoginRestAssuredIT() {
    }
    @BeforeClass
    public static void globalSetUp(){
        user = new User();
    }
    @Before
    public void setUp() {
        RestAssured.port = 8083;
        RestAssured.baseURI = "http://192.168.24.110";
        RestAssured.basePath = "/login/api/";
    }

    @Test
    public void testGetAll(){
        given().when().get("/users/all").then().statusCode(200);
    }
    /*
    @Test
    public void testAddDelivery(){

        deliveryId = given().contentType("application/json")
                .body(orderList).
                        when().
                        post("/add").
                        then().
                        extract().
                        path("deliveryId");
        Assert.assertNotEquals("",deliveryId);

    }
    @Test
    public void testGetDeliveryWithId(){
        given().
                accept(ContentType.JSON).
                pathParam("id",deliveryId).
                when().
                get("/{id}").
                then().
                statusCode(200).
                body("id", is(deliveryId));

    }
    @Test
    public void testGetDeliveryWithWrongId(){
        String id = "doesnt exists";
        given().
                accept(ContentType.JSON).
                pathParam("id",id).
                when().
                get("/{id}").
                then().
                statusCode(500);
    }
    @Test
    public void testDeleteDelivery(){
        given().
                pathParam("id", deliveryId).
                when().
                delete("/{id}").
                then().
                statusCode(500);
    }
    */
}

