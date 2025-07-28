package runner;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"stepDefinitions"},
    plugin = {"pretty",
    		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
    monochrome = true
    
)
public class TestRunner extends AbstractTestNGCucumberTests {
	
	@BeforeMethod
	@Parameters("browsers")
	public static void setUp(@Optional("chrome") String browser) {
	    System.setProperty("browser", browser);
	}

}
