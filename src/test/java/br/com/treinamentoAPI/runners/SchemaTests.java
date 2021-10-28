package br.com.treinamentoAPI.runners;

import br.com.treinamentoAPI.tests.booking.tests.GetBookingTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(br.com.treinamentoAPI.suites.SchemaTests.class)
@Suite.SuiteClasses({

        GetBookingTest.class,
})

public class SchemaTests {
}
