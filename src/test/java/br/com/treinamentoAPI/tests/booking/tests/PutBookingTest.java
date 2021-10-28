package br.com.treinamentoAPI.tests.booking.tests;

import br.com.treinamentoAPI.base.BaseTest;
import br.com.treinamentoAPI.suites.AllTests;
import br.com.treinamentoAPI.tests.auth.requests.PostAuthRequest;
import br.com.treinamentoAPI.tests.booking.requests.GetBookingRequest;
import br.com.treinamentoAPI.tests.booking.requests.PutBookingRequest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import static org.hamcrest.Matchers.greaterThan;

@Feature("Feature de Atualização de reservas")

public class PutBookingTest extends BaseTest {
    PutBookingRequest putBookingRequest = new PutBookingRequest();
    GetBookingRequest getBookingRequest = new GetBookingRequest();
    PostAuthRequest postAuthRequest = new PostAuthRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(AllTests.class)
    @DisplayName("Alterar uma reserva somente usando o Token")

    public void changeABookingOnlyUsingTheToken(){
        int primeiroId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        putBookingRequest.updateBookingToken(primeiroId, postAuthRequest.getToken() )
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(AllTests.class)
    @DisplayName("Tentar alterar uma reserva quando o token não for enviado")

    public void tryingToChangeAReservationWhenTheTokenIsNotSent(){
        int primeiroId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        putBookingRequest.updateBookingToken(primeiroId, "" )
                .then()
                .statusCode(403);

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(AllTests.class)
    @DisplayName("Tentar alterar uma reserva quando o token enviado for inválido")

        public void tryingToChangeAReservationWhenTheTokenSentIsInvalid(){

        int primeiroId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        putBookingRequest.updateBookingToken(primeiroId, getBookingRequest.toString())
                .then()
                .statusCode(403);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(AllTests.class)
    @DisplayName("Tentar alterar uma reserva que não existe")

    public void tryingToChangeAReservationThatDoesntExist() {

        putBookingRequest.updateBookingToken(123456789, postAuthRequest.getToken())
                .then()
                .statusCode(405);
    }


}
