import domain.Delivery;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;


public class DeliveryRestAssuredIT {

    String deliveryId = "";
    public DeliveryRestAssuredIT() {
    }
    @BeforeClass
    public static void globalSetUp(){
    }
    @Before
    public void setUp() {
        RestAssured.port = 8088;
        RestAssured.baseURI = "http://192.168.24.110";
        RestAssured.basePath = "/deliver/api/deliveries";
    }

    @Test
    public void mainTest(){
        testGetAll();
        testAddDelivery();
        testGetDeliveryWithId();
        testGetDeliveryWithWrongId();
        testDeleteDelivery();
    }

    public void testGetAll(){
        given().when().get("").then().statusCode(200);
    }


    public void testAddDelivery(){
        Set<String> orderList = new HashSet<>();
        orderList.add("test");
        deliveryId = given().contentType("application/json")
                .body(orderList).
                when().
                post("/add").
                then().
                extract().
                path("deliveryId");
        Assert.assertNotEquals("",deliveryId);

    }

    public void testGetDeliveryWithId(){
        given().
                accept(ContentType.JSON).
                pathParam("id",deliveryId).
                when().
                get("/{id}").
                then().
                statusCode(200).
                body("deliveryId", is(deliveryId));

    }


    public void testGetDeliveryWithWrongId(){
        String id = "doesnt exists";
        Response testRepsone = (Response) given().
                accept(ContentType.JSON).
                pathParam("id",id).
                when().
                get("/{id}").
                then().
                extract();
        String testRepsonestring = testRepsone.getContentType().toString();
        Assert.assertEquals("",testRepsonestring);
    }

    public void testDeleteDelivery(){
        given().
                pathParam("id", deliveryId).
                when().
                delete("/{id}").
                then().
                statusCode(200);
    }
}
