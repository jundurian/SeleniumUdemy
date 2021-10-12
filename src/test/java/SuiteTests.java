import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(org.junit.platform.runner.JUnitPlatform.class)
@SelectClasses({
        TesteAlert.class
})
public class SuiteTests {
}
