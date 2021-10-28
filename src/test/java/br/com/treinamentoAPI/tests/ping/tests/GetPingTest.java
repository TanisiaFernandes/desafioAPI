package br.com.treinamentoAPI.tests.ping.tests;

import br.com.treinamentoAPI.base.BaseTest;

import br.com.treinamentoAPI.suites.AllTests;
import br.com.treinamentoAPI.suites.HealthcheckTests;
import br.com.treinamentoAPI.tests.ping.requests.GetPingRequest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Feature("Feature de Api online")
public class GetPingTest extends BaseTest {

    GetPingRequest getPingRequest = new GetPingRequest();

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, HealthcheckTests.class})
    @DisplayName("Verificar se a Api está online")

    public void validateApiOnline() {
        getPingRequest.pingReturnApi()
                .then()
                .statusCode(201);
    }


}
