package stepDefinitions;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import factory.BrowserFactory;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

	private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	public Logger logger = LogManager.getLogger(this.getClass());
	public Properties p;

	public static WebDriver getDriver() {
		return driver.get();
	}

	@Before
	public void setup() throws IOException {
		FileReader file = new FileReader("./src//test//resources//config.properties");
		p = new Properties();
		p.load(file);
		
		String browser = System.getProperty("browser", "chrome"); // default to chrome
		String os = System.getProperty("os", "windows");
		String exe_env = p.getProperty("execution_env", "local");
		String gridURL=p.getProperty("gridurl");

		driver.set(BrowserFactory.initBrowser(browser, os, exe_env, gridURL));
		getDriver().get(p.getProperty("url"));
		getDriver().manage().window().maximize();
		logger.info("Browser is open" + browser);
	}

	@After
	public void tearDown() {
		if (getDriver() != null) {
			getDriver().quit();
			logger.info("Browser is close");

		}

	}

	@AfterStep
	public void takesScreenshot(Scenario scenario) throws IOException {
		if (scenario.isFailed() && getDriver() != null) {
			String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			String screenshotName = scenario.getName().toUpperCase().replaceAll(" ", "_") + "_" + timestamp + ".png";
			String screenshotPath = System.getProperty("user.dir") + "/target/screenshot/" + screenshotName;

			File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
			File destFile = new File(screenshotPath);
			destFile.getParentFile().mkdirs(); // create folder if doesn't exist
			Files.copy(srcFile.toPath(), destFile.toPath());

		    //Attach to Cucumber report
            byte[] screenshotBytes = Files.readAllBytes(destFile.toPath());
            scenario.attach(screenshotBytes, "image/png", screenshotName);
            
         // Attach to Extent Report
            String extentPath="./screenshot/"+screenshotName;
            com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(extentPath);


		}
	}
}
