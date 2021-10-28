package br.com.treinamentoAPI.tests.booking.requests;

import br.com.treinamentoAPI.base.BaseTest;
import br.com.treinamentoAPI.tests.booking.payloads.BookingPayloads;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class PutBookingRequest extends BaseTest {
    BookingPayloads bookingPayloads = new BookingPayloads();

    public Response updateBookingToken(int id, String token){
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("cookie", token)
                .when()
                .body(bookingPayloads.payLoadValidBooking().toString())
                .put("booking/"+id);

    }
}
