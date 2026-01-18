package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
    features = {"@target/rerun/failed_Scenarios.txt"},
    glue = {"com.qa.mystepdefs"},
    		plugin = {
    			    "html:target/rerun-report/html",             // Folder to hold HTML
    			    "json:target/rerun-report/rerun.json"        // JSON report inside separate folder
    			},
    monochrome = true
)
public class RerunFailed extends AbstractTestNGCucumberTests {

    // Required for TestNG + Data-driven execution
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
