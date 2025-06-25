package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage {

	WebDriver driver;
	@FindBy(id="email") private WebElement emailid;
	@FindBy(id="passwd") private WebElement pwd;
	@FindBy(id="SubmitLogin") private WebElement signinBtn;
	
	public SignInPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void logincred() {
		emailid.sendKeys("abc@fake.com");
		pwd.sendKeys("12345");
	
	}
	
	public void clickSignin()
	{
		signinBtn.click();
	}
}
