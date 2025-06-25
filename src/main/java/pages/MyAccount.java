package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccount {

	WebDriver driver;
	@FindBy(css="i.icon-home") private WebElement homeIcon;
	@FindBy(xpath = "//img[@alt='My Shop']") private WebElement logo;

	public boolean isLogoDisplayed() {
		return logo.isDisplayed();
	}

	public MyAccount(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickHomeIcon() {
		homeIcon.click();
	}
}
