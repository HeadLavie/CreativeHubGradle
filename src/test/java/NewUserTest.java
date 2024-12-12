import Models.User;
import Models.UserResponse;
import Utils.*;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class NewUserTest {

    private static Header applicationJsonHeader;
    private static User user;
    private static User invalidUser1;
    private static User invalidUser2;
    private static User invalidUser3;
    private static User invalidUser4;

    @BeforeAll
    static void setUp() {
        applicationJsonHeader = new Header("Content-Type", "application/json");
        user = new User();
        invalidUser1 = new User();
        invalidUser2 = new User();
        invalidUser3 = new User();
        invalidUser4 = new User();
        invalidUser1.setPassword(DataGenerator.generateInValidPassword());
        invalidUser2.setFirst_name(DataGenerator.generateInvalidFirstName());
        invalidUser3.setSecond_name(DataGenerator.generateInvalidSecondName());
        invalidUser4.setEmail(DataGenerator.generateInvalidEmail());

        RestAssured.baseURI = "https://ch-dev.testant.online";
    }

    @Test
    @Order(1)
    void shouldPostUser() {

        given().log().all().header(applicationJsonHeader)
                .body(user).when().post("ch/v1/user/")
                .then().assertThat().statusCode(201);

    }

    @Test
    @Order(2)
    void shouldPostExistingUser() {

        given().log().all().header(applicationJsonHeader)
                .body(user).when().post("ch/v1/user/")
                .then().assertThat().statusCode(400)
                .body("detail", equalTo("Email " + user.getEmail() + " is already associated with an account."));

    }

    @Test
    void shouldNotPostUserInvalidPassword() {

        given().header(applicationJsonHeader)
                .body(invalidUser1).when().post("ch/v1/user/")
                .then().assertThat().statusCode(422)
                .body("detail[0].type", equalTo("string_too_short"));

    }

    @Test
    void shouldNotPostUserInvalidFirstName() {

        given().header(applicationJsonHeader)
                .body(invalidUser2).when().post("ch/v1/user/")
                .then().assertThat().statusCode(422)
                .body("detail[0].msg", equalTo("Field required"));

    }

    @Test
    void shouldNotPostUserInvalidSecondName() {

        String response = given().header(applicationJsonHeader)
                .body(invalidUser3).when().post("ch/v1/user/")
                .then().assertThat().statusCode(422)
                .body("detail[0].type", equalTo("missing"))
                .extract().body().asString();

        System.out.println(response);

      // не совпадает в постмане
    }

    @Test
    void shouldNotPostUserInvalidEmail() {

        given().header(applicationJsonHeader)
                .body(invalidUser3).when().post("ch/v1/user/")
                .then().assertThat().statusCode(422);

    }


}