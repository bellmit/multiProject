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

    DeliveryOrder order;

    public OrderRestAssuredIT() {

    }

    @BeforeClass
    public void globalSetUp(){
        order = new DeliveryOrder();
    }
    @Before
    public void setUp() {
        RestAssured.port = 8080;
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "/order/api/";
    }


    @Test
    public void testAddOrder(){
        given().
                contentType("application/json").
                body(order).
        when().
                post("/deliveryorders/new").
        then().
                statusCode(200);
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

