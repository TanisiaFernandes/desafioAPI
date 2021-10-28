package br.com.treinamentoAPI.runners;

import br.com.treinamentoAPI.suites.AcceptanceTests;
import br.com.treinamentoAPI.tests.auth.tests.PostAuthTest;
import br.com.treinamentoAPI.tests.booking.tests.AcceptanceBookingTests;
import br.com.treinamentoAPI.tests.booking.tests.DeleteBookingTests;
import br.com.treinamentoAPI.tests.booking.tests.GetBookingTest;
import br.com.treinamentoAPI.tests.booking.tests.PutBookingTest;
import br.com.treinamentoAPI.tests.ping.tests.GetPingTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(br.com.treinamentoAPI.suites.AllTests.class)
@Suite.SuiteClasses({
        GetPingTest.class,
        GetBookingTest.class,
        PostAuthTest.class,
        DeleteBookingTests.class,
        PutBookingTest.class,
        AcceptanceBookingTests.class,

})

public class AllTests {
}
