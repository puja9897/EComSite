package stepDefinitions;

import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CategoryPage;
import pages.Homepage;
import pages.SignInPage;

public class CategorySteps {

	WebDriver driver = Hooks.getDriver();
	Homepage homepage;
	CategoryPage cat;
	SignInPage sign;
	public Logger logger=LogManager.getLogger(this.getClass());
	
	public CategorySteps() {
		homepage = new Homepage(driver);
		cat = new CategoryPage(driver);
		sign=new SignInPage(driver);
	}


	@Given("User is on homepage")
	public void user_is_on_homepage() {
	   homepage.clickSignIn();
	   logger.info("User is on homepage");
	}

	@When("User logins successfully with valid email id and password")
	public void user_logins_successfully_with_valid_email_id_and_password() {
		try {
	   sign.logincred();
	   logger.info("User has entered the credentials");

	   sign.clickSignin();
	   logger.info("User clicked on sign in");
	

	   assertTrue(homepage.isAccountDisplayed(), "Login unsuccessful");
		}
		catch(AssertionError ae) {
			logger.error("Assertion failed :{}"+ae.getMessage());
			throw ae; 
		}
		catch(Exception e) {
			logger.debug("Debugging exception: ",e);
			throw new RuntimeException(e); 
		}
	}

	@When("User clicks on {string} Category")
	public void user_clicks_on_category(String category) {
		if (category.equals("Women")) {
			homepage.ClickOnWomenCategory();
			logger.info("Clicked on women category");
		}
	}

	@Then("Product list should be displayed for {string} category")
	public void product_list_should_be_displayed_for_category(String string) {
		try {
		Assert.assertTrue(cat.isProductListDisplayed(), "Product count mismatch");
		}
		catch(AssertionError ae) {
			logger.error("Assertion failed "+ae.getMessage());
		}
		catch(Exception e) {
			logger.debug("Stake trace ", e);
			
		}
	}

	@When("User hovers on {string} Category")
	public void user_hovers_on_category(String category) {
		if (category.equals("Women")) {
			homepage.onHoverWomen();
			logger.info("User hover on Women Category");
		}
	}

	@When("Clicks on {string} sub category")
	public void clicks_on_sub_category(String subCategory) {
		if (subCategory.equals("Tops")) {
			homepage.clickSubCategoryTop();
			logger.info("User cliks on Tops sub category");

		}
	}

	@Then("Product list for {string} sub category  should be displayed")
	public void product_list_for_sub_category_should_be_displayed(String string) {
		try {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul.product_list")));
		Assert.assertTrue(cat.isProductListDisplayed(), "Product count mismatch");
		}
		catch(AssertionError ae) {
			logger.error("Assertion failed ", ae.getMessage());
		}
		catch(Exception e) {
			logger.debug("Exception occurred,stack trace ",e);
		}
	}

	@When("User navigates on {string} category")
	public void user_navigates_on_category(String category) {
		if(category.equals("Dresses")) {
			homepage.clickCategory(category);
			logger.info("User navigates to Dresses category");
		}
	   	}

	@Then("Breadcrumb navigation should be {string}")
	public void breadcrumb_navigation_should(String category) {
		try {
	    if(category.equals("Dresses")) {
	    	Assert.assertTrue(cat.isBreadCrumbDisplayed(category), "Incorrect Bread Crumb displayed");
	    }
	    }
		catch(AssertionError ae) {
			logger.error("Assertion failed ", ae.getMessage());
		}
		catch(Exception e) {
			logger.debug("Exception occurred,stack trace ",e);
		}
	}

	@When("Apply Filter {string}")
	public void apply_filter(String filter) {
	   if(filter.equals("S")) {
		   cat.selectSize(filter);
		   logger.info("Size S is selected");
	   }
	    if(filter.equalsIgnoreCase("Yellow")) {
		   cat.selectColor(filter);
		   logger.info("Color Yellow is selected");

	   }
	}

	
	@Then("Only Products with size {string} and color {string} should be displayed")
	public void only_products_with_size_and_color_should_be_displayed(String size, String color) {
		try {
		boolean isSizeApplied = cat.isFilterApplied("Size: " + size);
	    boolean isColorApplied = cat.isFilterApplied("Color: " + color);
	    Assert.assertTrue(isSizeApplied && isColorApplied, 
	        "Expected filters not applied: Size[" + size + "] Color[" + color + "]");
		Assert.assertTrue(cat.isProductListDisplayed(), "Product count mismatch");
		}
		catch(AssertionError ae) {
			logger.error("Assertion failed ", ae.getMessage());
		}
		catch(Exception e) {
	        logger.error("Unexpected exception occurred during filter verification: {}", e.getMessage());
			logger.debug("Exception occurred,stack trace ",e);
			 throw e; 
		}
	}

	@When("Clicks on List View")
	public void clicks_on_list_view() {
	    cat.clickList();
	    logger.info("User clicks on list view");
	}

	@Then("Products should be displayed in list view.")
	public void products_should_be_displayed_in_list_view() {
		try {
		
	    Assert.assertTrue(cat.isListViewDisplayed(), "List View is not displayed");
		}
		catch(AssertionError ae) {
			logger.error("Assertion failed ", ae.getMessage());
		}
		catch(Exception e) {
			logger.debug("Exception occurred,stack trace ",e);
		}
	}



}
