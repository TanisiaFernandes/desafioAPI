package br.com.treinamentoAPI.runners;

import br.com.treinamentoAPI.tests.auth.tests.PostAuthTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(br.com.treinamentoAPI.suites.SmokeTests.class)
@Suite.SuiteClasses({

        PostAuthTest.class,
})

public class SmokeTests {
}
