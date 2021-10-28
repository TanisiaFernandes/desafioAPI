package br.com.treinamentoAPI.runners;

import br.com.treinamentoAPI.tests.booking.tests.DeleteBookingTests;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(br.com.treinamentoAPI.suites.ContractTests.class)
@Suite.SuiteClasses({

        AcceptanceTests.class,
        DeleteBookingTests.class,
        AllTests.class

})

public class E2ETests {
}
