package br.com.treinamentoAPI.tests.booking.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class GetBookingRequest {

    @Step("Retornar o id da listagem de reservas")

    public Response bookingReturnIds(){
        return given()
                 .header("Content-Type", "application/json")
                 .when()
                 .get("booking");
    }

    @Step("Retornar uma reserva específica")

    public Response returnSpecificBooking(int id){
            return given()
                    .header("Content-Type", "application/json")
                    .when()
                    .get("booking/" + id);
        }


}
