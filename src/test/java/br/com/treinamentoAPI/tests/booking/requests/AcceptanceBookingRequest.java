package br.com.treinamentoAPI.tests.booking.requests;

import br.com.treinamentoAPI.base.BaseTest;
import br.com.treinamentoAPI.tests.booking.payloads.BookingPayloads;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class AcceptanceBookingRequest extends BaseTest {

    @Step("Listar id das reservas")

    public Response bookingReturnIds() {

        return given()
                .header("Content-Type", "application/json")
                .when()
                .get("booking");
    }

    @Step("Listar uma reserva específica")

    public Response returnSpecificBooking(int id) {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .get("booking/" + id);
    }

    @Step("Listar IDs de reservas utilizando o filtro firstname")

    public Response returnIdsFilterFirstName(String firstname) {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .get("booking?firstname=" + firstname);

    }

    @Step("Listar IDs de reservas utilizando o filtro lastname")

    public Response returnIdsFilterLastName(String lastname) {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .get("booking?firstname=" + lastname);

    }

    @Step("Listar IDs de reservas utilizando o filtro checkin")

    public Response returnIdsFilterCheckIn(String checkin) {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .get("booking?checkin=" + checkin);
    }

    @Step("Listar IDs de reservas utilizando o filtro checkout")

    public Response returnIdsFilterCheckOut(String checkout) {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .get("booking?checkout=" + checkout);
    }


    @Step("Listar IDs de reservas utilizando o filtro checkout and checkout")

    public Response returnFilterChekoutAndChekout(String checkout1, String checkout2){
        return given()
                .queryParam("checkout", checkout1)
                .queryParam("checkout", checkout2)
                .get("booking");
    }

    @Step("Listar IDs de reservas utilizando o filtro name, checkin and checkout date")

    public Response returnFilterNameAndCheckinAndCheckout(String name,String checkin, String checkout){
        return given()
                .queryParam("name", name)
                .queryParam("checkin", checkin)
                .queryParam("checkout", checkout)
                .get("booking");

    }

    @Step("Criar uma nova reserva")

    public Response criateNewBooking(String payload){
        return given()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .when()
                .body(payload)
                .post("booking");
    }

    @Step("Alterar uma reserva usando o token")

    public Response updateBookingToken(int id, String token){
        return given()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .header("Cookie",token)
                .when()
                .body(BookingPayloads.payLoadValidBooking().toString())
                .put("booking/"+ id);
    }

    @Step("Alterar uma reserva usando o Basic Auth")

    public Response updateBookingBasicAuth(int id) {
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorisation", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .when()
                .body(BookingPayloads.payLoadValidBooking().toString())
                .put("booking/" + id);
    }




}