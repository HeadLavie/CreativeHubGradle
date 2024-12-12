import Models.User;
import Models.UserResponse;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;


public class GetUserTest {


    private static Header applicationJsonHeader;
    private static User user;


    @BeforeAll
    static void setUp() {
        applicationJsonHeader = new Header("Content-Type", "application/json");
        user = new User();
        RestAssured.baseURI = "https://ch-dev.testant.online";
    }

    @Test
    void shouldPostUser() {

        given().log().all().header(applicationJsonHeader)
                .body(user).when().post("ch/v1/user/")
                .then().assertThat().statusCode(201).extract().response().as(UserResponse.class);

        System.out.println("User ID: " + UserResponse.uid);
        System.out.println("User name: " + UserResponse.first_name);

        // chatgpt сказал использовать UserResponse.gitUid()
    }

    @Test
    void shouldGetUser() {
        given().when().get("ch/v1/user/" + UserResponse.uid)
                .then().log().all().assertThat().statusCode(200)
                .body("first_name", equalTo(UserResponse.first_name))
                .body("second_name", equalTo(UserResponse.second_name));

/*        given().when().get("/ch/v1/user/" + UserResponse.uid)
                .then().assertThat().statusCode(200).body("first_name", equalTo(UserResponse.first_name))
                .body("second_name", equalTo(UserResponse.second_name))
                .body("email", equalTo(UserResponse.email))
                .body("uid", equalTo(UserResponse.uid))
                .body("username", equalTo(UserResponse.first_name + "_" + UserResponse.second_name))
                .body("contact_emails[0]", equalTo(UserResponse.email))
                .body("last_visited_at", nullValue())
                .extract().response().asString();*/

    }


}
