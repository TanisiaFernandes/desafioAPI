package br.com.treinamentoAPI.tests.booking.tests;

import br.com.treinamentoAPI.base.BaseTest;
import br.com.treinamentoAPI.suites.AcceptanceTests;
import br.com.treinamentoAPI.suites.AllTests;
import br.com.treinamentoAPI.suites.E2ETests;
import br.com.treinamentoAPI.tests.auth.requests.PostAuthRequest;
import br.com.treinamentoAPI.tests.booking.requests.DeleteBookingRequest;
import br.com.treinamentoAPI.tests.booking.requests.GetBookingRequest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DeleteBookingTests extends BaseTest {

    DeleteBookingRequest deleteBookingRequest = new DeleteBookingRequest();
    GetBookingRequest getBookingRequest = new GetBookingRequest();
    PostAuthRequest postAuthRequest = new PostAuthRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Excluir uma Reserva")

    public void deleteBooking() {
        int primeiroId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        deleteBookingRequest.deleteBooking(primeiroId, postAuthRequest.getToken())
                .then()
                .statusCode(201);

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class, E2ETests.class})
    @DisplayName("Tentar excluir um reserva que não existe")

    public void deleteBookingNonExistent(){

        deleteBookingRequest.deleteBooking(123456789, postAuthRequest.getToken())
                .then()
                .statusCode(405);

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class, E2ETests.class})
    @DisplayName("Tentar excluir uma reserva sem autorização")

    public void deleteBookingWithoutAuthorization(){
        int primeiroId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");


        deleteBookingRequest.deleteBooking(primeiroId,"BookingWithoutAuthorization")
                .then()
                .statusCode(403);

    }
}
