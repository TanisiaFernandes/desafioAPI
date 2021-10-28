package br.com.treinamentoAPI.runners;

import br.com.treinamentoAPI.tests.ping.tests.GetPingTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(br.com.treinamentoAPI.suites.HealthcheckTests.class)
@Suite.SuiteClasses({

        GetPingTest.class,

})

public class HealthcheckTests {

}
