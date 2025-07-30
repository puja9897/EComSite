package runner;

import org.testng.annotations.BeforeClass;
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
 //   tags = "@Regression"
    
)
public class TestRunner extends AbstractTestNGCucumberTests {
	
	@BeforeClass
	@Parameters({"browsers","os"})
	public void setUp(@Optional("chrome") 
	String browser,
			@Optional("windows") String os) {
	    System.setProperty("browser", browser);
	    System.setProperty("os", os);

	}

}
