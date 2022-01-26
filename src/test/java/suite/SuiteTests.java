package suite;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;
import tests.TesteAlert;

@RunWith(org.junit.platform.runner.JUnitPlatform.class)
@SelectClasses({
        TesteAlert.class
})
public class SuiteTests {
}
