package br.com.treinamentoAPI.runners;

import br.com.treinamentoAPI.suites.AllTests;
import br.com.treinamentoAPI.tests.booking.tests.AcceptanceBookingTests;
import br.com.treinamentoAPI.tests.booking.tests.DeleteBookingTests;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(br.com.treinamentoAPI.suites.AcceptanceTests.class)
@Suite.SuiteClasses({

        DeleteBookingTests.class,
        AcceptanceBookingTests.class,
        AllTests.class,
})

public class AcceptanceTests {
}
