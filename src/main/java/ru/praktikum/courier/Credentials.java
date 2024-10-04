package ru.praktikum.courier;

import com.google.gson.Gson;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static ru.praktikum.CONST.*;

public class Credentials {

        private String login;
        private String password;
        public int courierId;


        public Credentials() {

        }

        public Credentials(String login, String password) {
            this.login = login;
            this.password = password;
        }

        public static Credentials from(Courier courier) {
            return new Credentials(courier.getLogin(), courier.getPassword());
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public void setPassword(String password) {
            this.password = password;
        }


        public String getLogin() {
            return login;
        }

        public String getPassword() {
            return password;
        }

    public void deleteCourierAccount() {
        given().log().all()
                .header("Content-type", "application/json")
                .baseUri(baseURI)
                .when()
                .delete(API_DELETECOURIER + courierId)
                .then().log().all().assertThat()
                .statusCode(OK200);
        System.out.println("Учетная запись курьера успешно удалена");
    }

    public void courierIdForDelete(Courier courier) {
        Gson gson = new Gson();
        String json = gson.toJson(Credentials.from(courier));
        courierId = given().log().all()
                .header("Content-type", "application/json")
                .baseUri(baseURI)
                .body(json)
                .when()
                .post(API_COURIER_LOGIN)
                .then().log().all().assertThat()
                .statusCode(OK200)
                .extract()
                .path("id");
        System.out.println("id курьера: " + courierId);
    }


 }

