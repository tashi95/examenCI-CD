import com.fasterxml.jackson.databind.ser.Serializers;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojo.user;

import static io.restassured.RestAssured.*;
import static org.hamcrest.core.IsEqual.equalTo;

public class UserTests extends BaseTest {

    private String name;

    private static String RESOURCE = "/v1/examen/randomName";

    @Test
    public void RandomNameSinBody() {

        request
                .put(String.format("https://api-coffee-testing.herokuapp.com/v1/examen/updateName"))
        .then()
                .statusCode(406)
                .body("message",equalTo("Name was not provided"));
    }

    @Test
    public void sincredenciales() {

        request
                .get(String.format("%s",RESOURCE))
        .then()
                .statusCode(401)
                .body("message",equalTo("Please login first"));

        System.out.println(name);
    }

        @Test
        public void ObtenerName() {

            name =
                    given()
                    .auth()
                    .basic("testuser","testpass")
                    .get(String.format("%s",RESOURCE))
            .then()
                    .extract()
                    .path("name");

            System.out.println(name);
        }

    @Test
    public void sameName() {

        request
                .body("{\"name\":\""+name+"\"}")
                .post(String.format("https://api-coffee-testing.herokuapp.com/v1/examen/sameName"))
                .then()
                .statusCode(200)
                .body("name",equalTo(name));

        System.out.println(name);
    }

    }




