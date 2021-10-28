package br.com.treinamentoAPI.tests.auth.requests;

import br.com.treinamentoAPI.tests.auth.payloads.AuthPayloads;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class PostAuthRequest {

    AuthPayloads authPayLoads = new AuthPayloads();

    @Step("Retornar o Token")
    public Response tokenReturn(){
        return given()
                .header("Content-Type", "application/json" )
                .when()
                .body(authPayLoads.jsonAuthLogin().toString())
                .post("auth");
    }

    @Step("Buscar o Token")
    public String getToken(){
        return "token="+this.tokenReturn()
                .then()
                .statusCode(200)
                .extract()
                .path("token");
        }
}
