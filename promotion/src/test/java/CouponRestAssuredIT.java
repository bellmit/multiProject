import domain.Coupon;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;


import static io.restassured.RestAssured.given;

public class CouponRestAssuredIT {
    Coupon coupon;
    public CouponRestAssuredIT() {
    }
    @BeforeClass
    public void globalSetUp(){
        coupon = new Coupon();
    }
    @Before
    public void setUp() {
        RestAssured.port = 8080;
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "/promotion/api/";
    }

    @Test
    public void testGetAll(){
        given().when().get("/getall").then().statusCode(200);
    }

    @Test
    public void testAddCoupon(){
        String token = "";
        given().contentType("application/json")
                .header("Authorization","Bearer "+ token)
                .body(coupon).
                when().
                post("/new").
                then().
                statusCode(200);

    }
    @Test
    public void testGetCouponWithId(){
        String id = coupon.getId();
        given().
                accept(ContentType.JSON).
                pathParam("id",id).
                when().
                get("/{id}").
                then().
                statusCode(200).
                body("id", is(id));

    }
    @Test
    public void testGetCouponWithWrongId(){
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
    public void testDeleteCoupon(){
        given().
                contentType("application/json").
                body(coupon.getId()).
                when().
                delete("/delete").
                then().
                statusCode(500);
    }

}
