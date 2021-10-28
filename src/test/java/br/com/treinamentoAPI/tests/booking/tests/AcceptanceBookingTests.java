package br.com.treinamentoAPI.tests.booking.tests;

import br.com.treinamentoAPI.base.BaseTest;
import br.com.treinamentoAPI.suites.AllTests;
import br.com.treinamentoAPI.suites.E2ETests;
import br.com.treinamentoAPI.suites.SchemaTests;
import br.com.treinamentoAPI.tests.auth.requests.PostAuthRequest;
import br.com.treinamentoAPI.tests.booking.payloads.BookingPayloads;
import br.com.treinamentoAPI.tests.booking.requests.AcceptanceBookingRequest;
import br.com.treinamentoAPI.tests.booking.requests.GetBookingRequest;
import br.com.treinamentoAPI.utils.Utils;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import java.io.File;


import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.greaterThan;

public class AcceptanceBookingTests extends BaseTest {

    AcceptanceBookingRequest acceptanceBookingRequest = new AcceptanceBookingRequest();
    GetBookingRequest getBookingRequest = new GetBookingRequest();
    PostAuthRequest postAuthRequest = new PostAuthRequest();

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class})
    @DisplayName("Lista Id das reservas")

    public void listBookingId() {
        acceptanceBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, SchemaTests.class})
    @DisplayName("Listar uma reserva específica")

    public void listSpecificBookingId() {
        int primeiroId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        acceptanceBookingRequest.returnSpecificBooking(primeiroId)
                .then()
                .statusCode(200)
                .body(matchesJsonSchema(new File(Utils.getSchemaBasePath("booking", "returnSpecificBooking"))));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, SchemaTests.class})
    @DisplayName("Listar IDs de reservas utilizando o filtro firstname")

    public void returnIdsFilterFirstName(){
        int primeiroId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        String firstname = getBookingRequest.returnSpecificBooking(primeiroId)
                .then()
                .statusCode(200)
                .extract()
                .path("firstname");

        acceptanceBookingRequest.returnIdsFilterFirstName(firstname)
                .then()
                .statusCode(200)
                .body(matchesJsonSchema(new File(Utils.getSchemaBasePath("booking", "bookings"))));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, SchemaTests.class})
    @DisplayName("Listar IDs de reservas utilizando o filtro lastname")

    public void returnIdsFilterLastName(){
        int primeiroId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        String lastname = getBookingRequest.returnSpecificBooking(primeiroId)
                .then()
                .statusCode(200)
                .extract()
                .path("lastname");

        acceptanceBookingRequest.returnIdsFilterFirstName(lastname)
                .then()
                .statusCode(200)
                .body(matchesJsonSchema(new File(Utils.getSchemaBasePath("booking", "bookings"))));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, SchemaTests.class})
    @DisplayName("Listar IDs de reservas utilizando o filtro checkin")

    public void returnIdsFilterCheckIn(){
        int primeiroId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        String checkin = getBookingRequest.returnSpecificBooking(primeiroId)
                .then()
                .statusCode(200)
                .extract()
                .path("bookingdates.checkin");

        acceptanceBookingRequest.returnIdsFilterCheckIn(checkin)
                .then()
                .statusCode(200)
                .body(matchesJsonSchema(new File(Utils.getSchemaBasePath("booking", "bookings"))));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, SchemaTests.class})
    @DisplayName("Listar IDs de reservas utilizando o filtro checkout")

    public void returnIdsFilterCheckOut(){
        int primeiroId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        String checkout = getBookingRequest.returnSpecificBooking(primeiroId)
                .then()
                .statusCode(200)
                .extract()
                .path("bookingdates.checkout");

        acceptanceBookingRequest.returnIdsFilterCheckOut(checkout)
                .then()
                .statusCode(200)
                .body(matchesJsonSchema(new File(Utils.getSchemaBasePath("booking", "bookings"))));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, SchemaTests.class})
    @DisplayName("Listar IDs de reservas utilizando o filtro checkout and checkout")

    public void returnFilterChekoutAndChekout(){
        int primeiroId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        String checkout= getBookingRequest.returnSpecificBooking(primeiroId)
                .then()
                .statusCode(200)
                .extract()
                .path("bookingdates.checkout");

        acceptanceBookingRequest.returnFilterChekoutAndChekout("2021-01-01", checkout )
                .then()
                .statusCode(500);
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, SchemaTests.class})
    @DisplayName("Listar IDs de reservas utilizando o filtro name, checkin and checkout date")

    public void returnFilterNameAndCheckinAndCheckout(){
        int primeiroId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        Response filtrosReservas = getBookingRequest.returnSpecificBooking(primeiroId);

        String name = filtrosReservas.path("firstname");
        String checkin = filtrosReservas.path("bookingdates.checkin");
        String checkout = filtrosReservas.path("bookingdates.checkout");

        acceptanceBookingRequest.returnFilterNameAndCheckinAndCheckout(name,checkin, checkout)
                .then()
                .statusCode(200)
                .body(matchesJsonSchema(new File(Utils.getSchemaBasePath("booking", "bookings"))));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, SchemaTests.class})
    @DisplayName("Listar IDs de reservas utilizando o filtro checkout and checkout")

    public void returnIdsFilterCheckoutAndCheckout() {
        int primeiroId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        String checkout = getBookingRequest.returnSpecificBooking(primeiroId)
                .then()
                .statusCode(200)
                .extract()
                .path("bookingdates.checkout");

        acceptanceBookingRequest.returnFilterChekoutAndChekout(checkout, checkout)
                .then()
                .statusCode(500);
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class})
    @DisplayName("Criar Nova Reserva")

    public void createNewBooking(){
        acceptanceBookingRequest.criateNewBooking(BookingPayloads.payLoadValidBooking().toString())
                .then()
                .statusCode(200);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class})
    @DisplayName("Alterar uma reserva usando o token")

    public void validateChangeOfABookingUsingToken(){
        int primeiroId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        acceptanceBookingRequest.updateBookingToken(primeiroId,postAuthRequest.getToken());
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class})
    @DisplayName("Alterar uma reserva usando o Basic Auth")

    public void validateChangeOfABookingUsingBasicAuth(){
        int primeiroId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        acceptanceBookingRequest.updateBookingBasicAuth(primeiroId);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, E2ETests.class})
    @DisplayName("Visualizar erro de servidor 500 quando enviar filtro mal formatado")

    public void showErrorWhenSendingPoorlyFormattedFilter() {
        acceptanceBookingRequest.returnIdsFilterCheckOut("filtro inválido")
                .then()
                .statusCode(500);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, E2ETests.class})
    @DisplayName("Validar retorno 500 quando o payload da reserva estiver inválido")

    public void validateReturnWhenBookingPayloadIsInvalid(){
        acceptanceBookingRequest.returnIdsFilterCheckIn("payload de reserva inválido")
                .then()
                .statusCode(500);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, E2ETests.class})
    @DisplayName("Validar a criação de mais de um livro em sequencia")

    public void validateTheCreationOfMoreThanOneBookInSequence(){
        int primeiroBooking =acceptanceBookingRequest.criateNewBooking(BookingPayloads.payLoadValidBooking().toString())
                .then()
                .statusCode(200)
                .extract()
                .path("bookingid");
        int segundoBooking =acceptanceBookingRequest.criateNewBooking(BookingPayloads.payLoadValidBooking().toString())
                .then()
                .statusCode(200)
                .extract()
                .path("bookingid");

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, E2ETests.class})
    @DisplayName("Criar uma reserva enviando mais parâmetros no payload da reserva")

    public void createABookingBySendingMoreParametersOnThePayload(){
        JSONObject JSONPayload = BookingPayloads.payLoadValidBooking();
        JSONPayload.put("apelido","Tani");

        acceptanceBookingRequest.criateNewBooking(JSONPayload.toString())
                .then()
                .statusCode(200);

    }
}

