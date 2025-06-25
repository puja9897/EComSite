package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {

	public static WebDriver browser(String browser) {
		switch (browser) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			new ChromeDriver();
			break;

		case "edge":
			WebDriverManager.edgedriver().setup();
			new EdgeDriver();
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			new FirefoxDriver();
			break;

		default:
			System.out.println("Browser not supported or invalid browser.");
		}
		return null;

	}
}
