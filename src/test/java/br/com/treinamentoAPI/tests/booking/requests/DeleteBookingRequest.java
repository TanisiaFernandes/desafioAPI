package br.com.treinamentoAPI.tests.booking.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class DeleteBookingRequest {

    @Step("Excluir Reserva")

    public Response deleteBooking (int id, String token) {
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", token)
                .when()
                .delete("booking/" + id);
}

}
