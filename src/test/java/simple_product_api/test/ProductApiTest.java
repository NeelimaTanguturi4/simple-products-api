package simple_product_api.test;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ProductApiTest {

    //get all products of
    @Test
    public void testGetAllProducts() {

        RestAssured.baseURI = "http://localhost:8080";

        given()
                .when()
                .get("/products")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    // get particular products
    @Test
    public void testGetLaptop() {

        RestAssured.baseURI = "http://localhost:8080";

        given()
                .when()
                .get("/products")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("[0].name", equalTo("Laptop"))
                .body("[1].name", equalTo("Phone"));
    }

    // get one product
    @Test
    public void testGetProductById() {

        RestAssured.baseURI = "http://localhost:8080";

        given()
                .pathParam("id", 1)
                .when()
                .get("/products/{id}")
                .then()
                .statusCode(200)
                .body("id", equalTo(1));
    }


    @Test
    public void testGetinvalid() {

        RestAssured.baseURI = "http://localhost:8080";

        given()
                .when()
                .get("/products")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("[0].name", equalTo("Lap"));
    }

    @Test
    public void testAddProduct() {

            RestAssured.baseURI = "http://localhost:8080";

            String requestBody = """
     
            {
              "id": 9,
              "name": "Monitor",
              "price": 20000
            }
            """;

            given()
                    .contentType("application/json") //header
                    .body(requestBody)
                    .when()
                    .post("/products")
                    .then()
                    .statusCode(200)
                    .body("id", equalTo(9))
                    .body("name", equalTo("Monitor"))
                    .body("price", equalTo(20000.0f));
    }
}
