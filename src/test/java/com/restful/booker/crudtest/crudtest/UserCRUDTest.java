package com.restful.booker.crudtest.crudtest;

import com.restful.booker.model.BookingDates;
import com.restful.booker.model.RestPojo;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class UserCRUDTest {
    static String token = "4d9798b09e0e72e";
    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
         RestAssured.basePath = "/booking" ;
    }



    @Test
    public void verifyUserCreatedSuccessfully() {

        RestPojo restPojo = new RestPojo();
        restPojo.setFirstName("shaif");
       restPojo.setLastName("coco");
       restPojo.setTotalPrice(2000);
     restPojo.setDepositPaid(false);
       BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckInDate("12-12-2023");
        bookingDates.setCheckOutDate("23-12-2023");
        restPojo.setBookingdates(bookingDates);
        restPojo.setAdditionalNeeds("B & B");
        Response response = given()
                .headers("Content-Type", "application/json", "Authorization", "Bearer " + token)
                .when()
                .body(restPojo)
                .post();
//To fetch response from server
        response.then().log().all().statusCode(201);
        String responseBody = response.getBody().asString();

        JsonPath jsonPath = new JsonPath(responseBody);
        String bookingId = jsonPath.getString("id");
        System.out.println("bookingid " + bookingId);
        }
//@Test()
//public void verifyUserAuthSuccessfully() {
//        AuthPojo authPojo = new AuthPojo();
//        authPojo.setUsername("admin");
//        authPojo.setPassword("password123");
//
//        Response response = given()
//        .basePath("/auth")
//        .header("Content-Type", "application/json")
//        .when()
//        .body(authPojo)
//        .post();
//        String responseBody = response.getBody().asString();
//        response.then().log().all().statusCode(200);
//        }
//@Test
//public void getAllBookingId() {
//        Response response= given()
//        .basePath("/booking")
//        .header("Content-Type", "application/json")
//        .when()
//        .get();
//        response.then().statusCode(200);
//        response.prettyPrint();
//        }

}
