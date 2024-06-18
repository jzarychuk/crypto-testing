package cuccumbersOptions;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

// Configure Cucumber options using the @CucumberOptions annotation
@CucumberOptions(
		// Specify the path to the feature files
		features = "src/test/java/features/FileVisibilityTest.feature",
		// Specify the package containing the step definitions
		glue = "stepDefinitions",

		// Specify the plugins for generating reports
		plugin = { "pretty", "html:target/cucumber-reports.html", "json:target/cucumber-reports.json" },
		// Enable monochrome output for better readability in the console
		monochrome = true
// Uncomment the following line if you want the test to only check for missing
// step definitions and not run the tests
// dryRun = true
)

// Define the TestNGRunner class that extends AbstractTestNGCucumberTests to integrate Cucumber with TestNG
public class TestNGRunner extends AbstractTestNGCucumberTests {
	// No additional code needed, as configuration is handled by annotations and
	// superclass
}
