import domain.Delivery;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.jboss.arquillian.container.test.api.BeforeDeployment;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;


public class DeliveryRestAssuredIT {
    Delivery delivery ;

    String deliveryId = "";
    public DeliveryRestAssuredIT() {
    }
    @BeforeDeployment
    public void globalSetUp(){
        delivery = new Delivery();
    }
    @Before
    public void setUp() {
        RestAssured.port = 8080;
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "/delivery/api/";
    }

    @Test
    public void testGetAll(){
        given().when().get("").then().statusCode(200);
    }

    @Test
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
}
