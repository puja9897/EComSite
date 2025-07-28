package stepDefinitions;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import factory.BrowserFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

	private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	public Logger logger = LogManager.getLogger(this.getClass());
	public Properties p;

	public static WebDriver getDriver() {
		return driver.get();
	}

	@Before
	public void setup() throws IOException {
		FileReader file=new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		String browser = System.getProperty("browser", "chrome"); // default to chrome

		driver.set(BrowserFactory.initBrowser(browser));
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
}
