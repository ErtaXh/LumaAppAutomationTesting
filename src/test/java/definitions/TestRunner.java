package definitions;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/Features",glue={"definitions","hooks"}, plugin = {"pretty", "html:target/reports.html"},
        monochrome = true)
public class TestRunner {
}