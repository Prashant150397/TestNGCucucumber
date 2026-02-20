package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;


@CucumberOptions(
	    features = {"src/test/resources/features"},
	    glue = {"com.qa.mystepdefs"},
	    plugin = {
	        "pretty",
	        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
	        "html:target/cucumber-reports.html", 
	        "json:target/cucumber.json",
	        "rerun:target/rerun/failed_Scenarios.txt"
	    },
	   // tags = "(@Smoke or @Regression) and not @Sanity",
	    monochrome = true,
	    publish = true,
	    dryRun = false       // ✅ Must be false to actually run tests
	)
	public class TestRunner extends AbstractTestNGCucumberTests {
	    @Override
	    @DataProvider(parallel = true)
	    public Object[][] scenarios() {
	        return super.scenarios();
	    }
	}
