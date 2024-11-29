
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SampleTest {

    private static Header applicationJsonHeader;
    private static User user;
    private static String accessToken;  // Class-level variable for access token
    private static String refreshToken; // Class-level variable for refresh token
    private static Header tokenHeader;

    @BeforeAll
    static void setUp() {
        applicationJsonHeader = new Header("Content-Type", "application/json");
        user = new User();

        RestAssured.baseURI = "https://ch-dev.testant.online";
    }


    @Test
    @Order(1)
    void shouldPostUser() {

        given().log().all().header(applicationJsonHeader)

                // можно ли тут Header.class("Content-Type", "application/json")

                .body(user).when().post("ch/v1/user/")

                // body(class.User) не сработало

                .then().assertThat().statusCode(201).extract().response().as(UserResponse.class);

    }

    @Test
    @Order(2)
    void shouldGetUser() {

        given().when().get("/ch/v1/user/" + UserResponse.uid)
                .then().log().all().assertThat().body("first_name", equalTo(UserResponse.first_name));
    }

    @Test
    @Order(3)
    void shouldAuthUser() {

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("email", user.getEmail());
        requestBody.put("password", user.getPassword());

        String response = given().header(applicationJsonHeader).body(requestBody)
                // не очень понимаю Map

                .when().post("ch/v1/auth/login/")
                .then().assertThat().statusCode(200).body("token_type", equalTo("bearer"))
                .extract().response().asString();

        JsonPath js = ReUsableMethods.rawToJson(response);
        // JsonPath js = new JsonPath(response);
        accessToken = js.getString("access_token");
        refreshToken = js.getString("refresh_token");
        tokenHeader = new Header("Authorization", "Bearer " + refreshToken);
    }

    @Test
    @Order(4)
    void shouldRefreshToken() {

        String response = given().header(tokenHeader)
                .when().post("ch/v1/auth/refresh/").then().assertThat()
                .statusCode(200).body("access_token", not(equalTo(accessToken)))
                .extract().response().asString();


        JsonPath js = ReUsableMethods.rawToJson(response);
        accessToken = js.getString("access_token");

        // повторяются два теста

    }

    @Test
    @Order(5)
    void shouldResetPassword() {

        String email = user.getEmail();

        given().header(applicationJsonHeader).body(Map.of("email", email))
                .when()
                .post("ch/v1/reset-password/request/").then()
                .assertThat()
                .statusCode(200)
                .body("message", equalTo("Инструкция по сбросу пароля отправлена на вашу почту."));

    }


    @Test
    @Order(6)
    void shouldDeleteUser() {

        given().header("Authorization", "Bearer " + accessToken).when().delete("ch/v1/user/").then()
                .assertThat().statusCode(204);

    }
}