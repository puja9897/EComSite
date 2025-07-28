package stepDefinitions;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.Homepage;
import pages.MyAccount;
import pages.SignInPage;

public class LoginSteps { 

	WebDriver driver=Hooks.getDriver();
	Homepage homepage;
	SignInPage signin;
	MyAccount myaccount;
	public Logger logger=LogManager.getLogger(this.getClass());

	
	@Given("User is in login page")
	public void user_is_in_login_page() {
		logger.info("User is in login page");
		homepage=new Homepage(driver);
		homepage.clickSignIn();
		logger.info("User has clicked on sign in button");

	}

	@When("User enters email id and password")
	public void user_enters_email_id_and_password() throws IOException {
		signin=new SignInPage(driver);
		signin.logincred();
		logger.info("User has entered the credentials");

	}

	@When("click on Sign in button")
	public void click_on_sign_in_button() {
	signin.clickSignin();
	logger.info("User has clicked on sign in button");

	}

	@Then("USer is able to login successfully")
	public void u_ser_is_able_to_login_successfully() {
	try {
		myaccount=new MyAccount(driver);
		Assert.assertTrue(myaccount.isLogoDisplayed(), "Logo is not displayed");
		Assert.assertTrue(myaccount.isAccountNameDisplayed(),"Login failed");
		logger.info("User is able to login successfully. MyAccount logo is visible.");
	}
	catch(AssertionError ae) {
		logger.error("Assertion failed: {}",ae.getMessage());
	}
	catch(Exception e) {
        logger.error("Exception occurred while verifying login: {}", e.getMessage());
        logger.debug("Stack trace: ", e);
        throw e; // rethrow to fail the test
	}

		
	}

}
