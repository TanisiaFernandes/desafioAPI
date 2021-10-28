package br.com.treinamentoAPI.tests.booking.tests;

import br.com.treinamentoAPI.base.BaseTest;
import br.com.treinamentoAPI.suites.AllTests;
import br.com.treinamentoAPI.suites.ContractTests;
import br.com.treinamentoAPI.suites.SchemaTests;
import br.com.treinamentoAPI.tests.booking.requests.GetBookingRequest;
import br.com.treinamentoAPI.utils.Utils;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import java.io.File;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.greaterThan;

@Feature("Feature de Retorno de reservas")

public class GetBookingTest extends BaseTest {

    GetBookingRequest getBookingRequest = new GetBookingRequest();

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class})
    @DisplayName("Listar Ids de reservas")

    public void listBookingsIds() {
        getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, ContractTests.class, SchemaTests.class})
    @DisplayName("Garantir o Schema de retorno da listagem de reservas")

    public void ensureTheBookingListingReturnSchema() {
        getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .body(matchesJsonSchema(new File(Utils.getSchemaBasePath("booking", "bookings"))));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, SchemaTests.class})
    @DisplayName("Garantir o Schema de uma reserva específica")

    public void ensureTheSchemaOfASpecificBooking() {
        int primeiroId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        getBookingRequest.returnSpecificBooking(primeiroId)
                .then()
                .statusCode(200)
                .body(matchesJsonSchema(new File(Utils.getSchemaBasePath("booking", "returnSpecificBooking"))));
    }



}
