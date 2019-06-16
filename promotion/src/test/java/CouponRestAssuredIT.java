import domain.Coupon;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;


import static io.restassured.RestAssured.given;

public class CouponRestAssuredIT {
    static Coupon coupon;
    String path;
    public CouponRestAssuredIT() {
    }
    @BeforeClass
    public static void  globalSetUp(){
        coupon = new Coupon();
    }
    @Before
    public void setUp() {
        RestAssured.port = 8086;
        RestAssured.baseURI = "http://192.168.24.110";
        RestAssured.basePath = "/promotion/api/coupon";
        path = RestAssured.baseURI+RestAssured.port+RestAssured.basePath;
    }

    @Test
    public void testGetAll(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJub2V0ZG5peHNpXzE1NTQ4MDMxOTZAdGZibncubmV0Iiwicm9sZXMiOltdLCJpZCI6ImE2OWRiMTZmLWNmYTYtNDY1Ny1hMmExLTBlM2YwZGE3M2JlMSIsImV4cCI6MTU1OTU2MTY2OCwiaWF0IjoxNTU5NTU4MDY4fQ.cKp69RDGrOi709_FNGjIvO2TEF82hl2gl4G3UlYSqOA";
        given().header("Authorization","Bearer "+ token ).when().get("/getall").then().statusCode(200);
    }

    @Test
    public void testAddCoupon(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJub2V0ZG5peHNpXzE1NTQ4MDMxOTZAdGZibncubmV0Iiwicm9sZXMiOltdLCJpZCI6ImE2OWRiMTZmLWNmYTYtNDY1Ny1hMmExLTBlM2YwZGE3M2JlMSIsImV4cCI6MTU1OTU2MTY2OCwiaWF0IjoxNTU5NTU4MDY4fQ.cKp69RDGrOi709_FNGjIvO2TEF82hl2gl4G3UlYSqOA";
        given().contentType("application/json")
                .header("Authorization","Bearer "+ token)
                .body(coupon).
                when().
                post("/new").
                then().
                statusCode(403);

    }
    @Test
    public void testGetCouponWithId(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJub2V0ZG5peHNpXzE1NTQ4MDMxOTZAdGZibncubmV0Iiwicm9sZXMiOltdLCJpZCI6ImE2OWRiMTZmLWNmYTYtNDY1Ny1hMmExLTBlM2YwZGE3M2JlMSIsImV4cCI6MTU1OTU2MTY2OCwiaWF0IjoxNTU5NTU4MDY4fQ.cKp69RDGrOi709_FNGjIvO2TEF82hl2gl4G3UlYSqOA";
        String id = coupon.getId();
        given()
                .header("Authorization","Bearer "+ token).
                accept(ContentType.JSON).
                pathParam("id",id).
                when().
                get("/{id}").
                then().
                statusCode(404);//.
                //body("id", is(id));

    }
    @Test
    public void testGetCouponWithWrongId(){
        String id = "doesnt exists";
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJub2V0ZG5peHNpXzE1NTQ4MDMxOTZAdGZibncubmV0Iiwicm9sZXMiOltdLCJpZCI6ImE2OWRiMTZmLWNmYTYtNDY1Ny1hMmExLTBlM2YwZGE3M2JlMSIsImV4cCI6MTU1OTU2MTY2OCwiaWF0IjoxNTU5NTU4MDY4fQ.cKp69RDGrOi709_FNGjIvO2TEF82hl2gl4G3UlYSqOA";
        given()
                .header("Authorization","Bearer "+ token).
                accept(ContentType.JSON).
                pathParam("id",id).
                when().
                get("/{id}").
                then().
                statusCode(404);
    }
    @Test
    public void testDeleteCoupon(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJub2V0ZG5peHNpXzE1NTQ4MDMxOTZAdGZibncubmV0Iiwicm9sZXMiOltdLCJpZCI6ImE2OWRiMTZmLWNmYTYtNDY1Ny1hMmExLTBlM2YwZGE3M2JlMSIsImV4cCI6MTU1OTU2MTY2OCwiaWF0IjoxNTU5NTU4MDY4fQ.cKp69RDGrOi709_FNGjIvO2TEF82hl2gl4G3UlYSqOA";
        given().
                header("Authorization","Bearer "+ token).
                contentType("application/json").
                body(coupon.getId()).
                when().
                delete("/delete").
                then().
                statusCode(404);
    }

}
