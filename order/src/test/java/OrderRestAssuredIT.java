import domain.DeliveryOrder;
import domain.Order;
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

public class OrderRestAssuredIT {

    static DeliveryOrder order;

    public OrderRestAssuredIT() {

    }

    @BeforeClass
    public static void globalSetUp(){
        order = new DeliveryOrder();
    }
    @Before
    public void setUp() {
        RestAssured.port = 8084;
        RestAssured.baseURI = "http://192.168.24.110";
        RestAssured.basePath = "/order/api/deliveryorders";
    }
    @Test
    public void getAll(){
        given().when().get("/all/1").then().statusCode(200);
    }

    @Test
    public void testAddOrder(){
        given().
                contentType("application/json").
                body(order).
        when().
                post("/new").
        then().
                statusCode(500);
    }
    @Test
    public void testGetOrderWithId(){
        given().
                accept(ContentType.JSON).
                pathParam("id",order.getId()).
                when().
                get("/deliveryorders/{id}").
                then().
                statusCode(200).
                body("id", is(order.getId()));

    }
    @Test
    public void testGetOrderWithWrongId(){
        String id = "doesnt exists";
        given().
                accept(ContentType.JSON).
                pathParam("id",id).
                when().
                get("/deliveryorders/{id}").
                then().
                statusCode(500);
    }
    @Test
    public void testDeleteOrder(){
        given().
                contentType("application/json").
                body(order).
        when().
                delete().
        then().
                statusCode(500);
    }

}

