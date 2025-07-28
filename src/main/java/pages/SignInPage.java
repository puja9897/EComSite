package pages;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage {

	WebDriver driver;
	public Properties p;
	@FindBy(id="email") private WebElement emailid;
	@FindBy(id="passwd") private WebElement pwd;
	@FindBy(id="SubmitLogin") private WebElement signinBtn;
	
	public SignInPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void logincred() throws IOException {
		
		FileReader file=new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		emailid.sendKeys(p.getProperty("username"));
		pwd.sendKeys(p.getProperty("password"));
	
	}
	
	public void clickSignin()
	{
		signinBtn.click();
	}
}
