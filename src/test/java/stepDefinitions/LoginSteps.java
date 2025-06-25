package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.Homepage;
import pages.MyAccount;
import pages.SignInPage;

public class LoginSteps { 

	WebDriver driver=Hooks.driver;
	Homepage homepage;
	SignInPage signin;
	MyAccount myaccount;
	
	@Given("User is in login page")
	public void user_is_in_login_page() {
		homepage=new Homepage(driver);
		homepage.clickSignIn();
	}

	@When("User enters email id and password")
	public void user_enters_email_id_and_password() {
		signin=new SignInPage(driver);
		signin.logincred();
	}

	@When("click on Sign in button")
	public void click_on_sign_in_button() {
	signin.clickSignin();
	}

	@Then("USer is able to login successfully")
	public void u_ser_is_able_to_login_successfully() {
	
		myaccount=new MyAccount(driver);
		Assert.assertTrue(myaccount.isLogoDisplayed(), "Login fail");
		
	}

}
